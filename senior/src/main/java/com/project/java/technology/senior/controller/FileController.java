package com.project.java.technology.senior.controller;

import com.project.java.technology.core.model.ResponseCodeEnum;
import com.project.java.technology.core.model.ResponseModel;
import com.project.java.technology.senior.model.FileModel;
import com.project.java.technology.senior.service.IFileModelService;
import com.project.java.technology.senior.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.project.java.technology.senior.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: FileController
 * @Description:
 * @author: yinshaobo
 * @date: 2018/9/20 下午8:41
 */
@Api(value = "/file", description = "文件操作")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    private IFileModelService fileModelService;

    @PostMapping("/upload")
    public ResponseModel upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResponseModel.fail(ResponseCodeEnum.UPLOAD_FILE_EMPTY);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + suffixName;
        File dest = new File(uploadPath + newFileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //构建file model
            FileModel fileModel = new FileModel();
            String fileId = UUID.randomUUID().toString();
            fileModel.setOrigFileName(fileName);
            fileModel.setFileName(newFileName);
            fileModel.setFilePath(dest.getPath());
            fileModel.setFileId(fileId);
            fileModel.setRequestIP(HttpUtils.getIP(request));
            fileModel.setCreateTime(new Date());
            fileModel.setUpdateTime(new Date());
            this.fileModelService.insert(fileModel);

            return ResponseModel.success(fileModel);
        } catch (Exception e) {
            log.error("文件写入磁盘失败！", e);
            return ResponseModel.fail();
        }
    }

    @ApiOperation(value = "下载文件", notes = "根据文件ID下载文件")
    @ApiImplicitParam(name = "data_id", value = "文件ID", required = true, paramType = "query", dataType = "String")
    @GetMapping("/download")
    public ResponseModel download(@RequestParam("data_id") String fileId) {
        FileModel fileModel = this.fileModelService.findByFileId(fileId);
        if (fileModel == null) {
            return ResponseModel.fail(ResponseCodeEnum.FILE_NOT_EXIST);
        }
        String fileName = fileModel.getFileName();
        Map<String, String> resMap = new HashMap<>(16);
        resMap.put("file", Base64Utils.file2Base64(fileModel.getFilePath()));
        resMap.put("suffix", fileName.substring(fileName.lastIndexOf(".")));
        return ResponseModel.success(resMap);
    }

    @ApiOperation(value = "下载文件", notes = "根据文件路径下载文件")
    @ApiImplicitParam(name = "path", value = "文件路径Base64", required = true, paramType = "query", dataType = "String")
    @GetMapping("/download/local")
    public void downloadLocal(@RequestParam("path") String fileBase64Path, HttpServletResponse response) {
        String encodeMethodMsg = "org.springframework.util.Base64Utils.encodeToUrlSafeString(filePath.getBytes(java.nio.charset.Charset.defaultCharset())";
        FileInputStream inputStream = null;
        try {
            log.info("download local file, file path encode={}", fileBase64Path);
            String filePath = Base64Utils.decode(fileBase64Path);
            log.info("download local file path={}", filePath);
            File file = new File(filePath);
            if (!file.exists()) {
                outError("文件不存在！文件路径加密方式：" + encodeMethodMsg, response);
                return;
            }
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[4 * 1024];
            int readLength;
            this.setResponseHeader(response, file.getName());
            while (((readLength = inputStream.read(buf)) != -1)) {
                response.getOutputStream().write(buf, 0, readLength);
            }
        } catch (Exception e) {
            outError("文件下载失败！文件路径加密方式：" + encodeMethodMsg, response);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 设置响应头
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            response.reset();// 清空输出流
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "8859_1"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void outError(String msg, HttpServletResponse response) {
        try {
            //获取OutputStream输出流
            OutputStream outputStream = response.getOutputStream();
            //通过设置响应头控制浏览器以UTF-8的编码显示
            response.setHeader("content-type", "text/html;charset=UTF-8");
            //将字符转换成字节数组，指定以UTF-8编码进行转换
            byte[] dataByteArr = msg.getBytes("UTF-8");
            //使用OutputStream流向客户端输出字节数组
            outputStream.write(dataByteArr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

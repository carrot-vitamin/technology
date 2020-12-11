package com.project.java.technology.senior.groovy;

import com.alibaba.fastjson.util.IOUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author ShaoBo Yin at 2020/12/11 17:46
 * 实现用于groovy动态脚本运行的controller
 */
@Slf4j
@RestController
@RequestMapping("/groovy/script")
public class GroovyScriptController {

    @Autowired
    private Binding groovyBinding;

    private GroovyShell groovyShell;

    /**
     * 将binding对象注入后，在初始化方法init()中用binding对象构造GroovyShell对象，在提供的execute接口实现中用GroovyShell对象生成Script脚本对象，并调用Script的run()方法运行动态脚本并返回结果。
     * <p>
     * 示例中只是一个简单实现，在接口方法execute中，每次脚本运行前都会通过groovyShell来parse出一个Script 对象，这其实是有成本的，实际应用中可根据脚本特征（如md5值等）将script存储, 下次运行时可根据脚本特征直接获取Script对象，避免parse的成本。
     */
    @PostConstruct
    public void init() {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader());
        groovyShell = new GroovyShell(groovyClassLoader, groovyBinding);
    }

    /**
     * 示例接口：通过接口直接用groovy脚本调用了testService这个bean的方法
     *
     * @param request body 示例：def query = testService.testQuery(1L); \n query
     * @return Test query success, id is 1
     */
    @PostMapping(value = "/execute")
    public String execute(HttpServletRequest request) {
        Script script = groovyShell.parse(readAsChars(request));
        return String.valueOf(script.run());
    }

    public static String readAsChars(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.close(br);
        }
        return sb.toString();
    }

}

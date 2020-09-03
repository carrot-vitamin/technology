package com.project.java.technology.senior.service.impl;

import com.project.java.technology.senior.model.Mail;
import com.project.java.technology.senior.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Shaobo Yin
 * 2020/8/10 13:42
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    private JavaMailSenderImpl mailSender;

    @Override
    public void sendMail(Mail mail) throws Exception {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(465);
        mailSender.setUsername("XXX@163.com");
        mailSender.setPassword("XXX");
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.transport.protocol", "smtp");
        javaMailProperties.setProperty("mail.smtp.host", "smtp.163.com");
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(javaMailProperties);

        checkMail(mail);
        //true表示支持复杂类型
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
        //发件人
        messageHelper.setFrom("shaobo_yin@163.com");
        //收信人
        messageHelper.setTo(mail.getTo().split(","));
        //内容
        messageHelper.setText(mail.getText());
        //发送时间
        messageHelper.setSentDate(new Date());

        if (StringUtils.isNotBlank(mail.getSubject())) {
            //主题
            messageHelper.setSubject(mail.getSubject());
        }

        if (StringUtils.isNotBlank(mail.getCc())) {
            //抄送
            messageHelper.setCc(mail.getCc().split(","));
        }

        if (StringUtils.isNotBlank(mail.getCc())) {
            //密送
            messageHelper.setBcc(mail.getBcc().split(","));
        }

        if (mail.getFiles() != null) {
            for (MultipartFile file : mail.getFiles()) {
                String fileName = Objects.requireNonNull(file.getOriginalFilename());
                messageHelper.addAttachment(fileName, file);
            }
        }

        //正式发送邮件
        mailSender.send(messageHelper.getMimeMessage());
    }

    private void checkMail(Mail mail) {
        if (StringUtils.isBlank(mail.getTo())) {
            throw new RuntimeException("发送地址不能为空");
        }

        if (StringUtils.isBlank(mail.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }
}

package com.project.java.technology.senior.controller;

import com.project.java.technology.senior.model.Mail;
import com.project.java.technology.senior.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaobo Yin
 * 2020/8/10 14:08
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IMailService mailService;

    @PostMapping
    public Boolean sendMessage(Mail mail) {
        try {
            this.mailService.sendMail(mail);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}

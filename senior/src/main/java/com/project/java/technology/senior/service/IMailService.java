package com.project.java.technology.senior.service;


import com.project.java.technology.senior.model.Mail;

/**
 * @author Shaobo Yin
 * 2020/8/10 13:41
 */
public interface IMailService {

    void sendMail(Mail mail) throws Exception;
}

package com.ning.util.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 *
 * @author wangn
 */
public class EmailUtils {
    private EmailUtils() {
    }

    /**
     * 邮件发送者账户
     */
    private static final String EMAIL_SENDER_USER = "itning@itning.top";
    /**
     * 邮件发送者密码
     */
    private static final String EMAIL_SENDER_PWD = "";

    private static final String CHECK_EMAIL_STR = "@";

    /**
     * 1、创建连接对象
     * 设置邮件发送的协议
     * 设置发送邮件的服务器
     * 填写自己的密钥
     * 2、创建邮件对象
     * 设置发件人
     * 设置收件人
     * 设置抄送者
     * 设置邮件主题
     * 设置邮件内容
     * 3、发送邮件
     *
     * @param to      邮件接收者邮箱
     * @param subject 主题
     * @param content 内容
     */
    public static void sendEmail(String to, String subject, String content) throws MessagingException {
        if (!to.contains(CHECK_EMAIL_STR)) {
            throw new IllegalArgumentException("接收者邮箱非法,请检查!");
        }
        //1、创建连接对象
        Properties props = new Properties();
        //1.1设置邮件发送的协议
        props.put("mail.transport.protocol", "smtp");
        //1.2设置发送邮件的服务器
        props.put("mail.smtp.host", "smtp.mxhichina.com");
        //1.3需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");
        //1.4下面一串是发送邮件用465端口，如果不写就是以25端口发送，阿里云已经关闭了25端口
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        //1.5认证信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_SENDER_USER, EMAIL_SENDER_PWD);
            }
        });
        //2、创建邮件对象
        Message message = new MimeMessage(session);
        //2.1设置发件人
        message.setFrom(new InternetAddress(EMAIL_SENDER_USER));
        //2.2设置收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        //2.3设置抄送者（PS:没有这一条网易会认为这是一条垃圾短信，而发不出去）
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(EMAIL_SENDER_USER));
        //2.4设置邮件的主题
        message.setSubject(subject);
        //2.5设置邮件的内容
        message.setContent("" + content + "", "text/html;charset=utf-8");
        // 3、发送邮件
        Transport.send(message);
    }
}

package cn.tendata.crm.externalmail;

import cn.tendata.crm.data.domain.User;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by Luo Min on 2017/1/5.
 */
public abstract class MailSenderBuilder {

    public static MailSender mailSender(User user){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername(user.getMailBox());
        mailSender.setPassword(user.getPassword());
        return mailSender;
    }
}

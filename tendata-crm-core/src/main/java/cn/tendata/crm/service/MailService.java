package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Luo Min on 2016/12/7.
 */
public interface MailService extends EntityService<Mail,Long> {

    Page<Mail> getAllByReaded(User user, Boolean readed, Pageable pageable);
}

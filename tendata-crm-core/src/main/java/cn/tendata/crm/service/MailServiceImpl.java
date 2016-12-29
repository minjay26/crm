package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Luo Min on 2016/12/7.
 */
@Service
public class MailServiceImpl extends EntityServiceSupport<Mail,Long,MailRepository> implements MailService{

    @Autowired
    public MailServiceImpl(MailRepository repository) {
        super(repository);
    }

    public Page<Mail> getAllByReaded(User user,Boolean readed, Pageable pageable){
        return getRepository().findAllByToUserAndReaded(user,readed,pageable);
    }
}

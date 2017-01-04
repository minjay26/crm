package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.MailAttachment;
import cn.tendata.crm.data.repository.MailAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Luo Min on 2017/1/3.
 */
@Service
public class MailAttachmentServiceImpl extends EntityServiceSupport<MailAttachment,Long,MailAttachmentRepository> implements MailAttachmentService{

    @Autowired
    public MailAttachmentServiceImpl(MailAttachmentRepository repository) {
        super(repository);
    }
}

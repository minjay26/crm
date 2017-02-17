package cn.tendata.crm.service;

import cn.tendata.crm.data.mongodb.domain.ChatMessage;
import cn.tendata.crm.data.mongodb.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by minjay on 2017/2/17.
 */
@Service
public class ChatMessageServiceImpl extends EntityServiceSupport<ChatMessage,String,ChatMessageRepository> implements ChatMessageService{

    @Autowired
    public ChatMessageServiceImpl(ChatMessageRepository repository) {
        super(repository);
    }
}

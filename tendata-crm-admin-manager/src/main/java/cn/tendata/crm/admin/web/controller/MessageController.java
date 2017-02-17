package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.util.JsonUtils;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.mongodb.domain.ChatMessage;
import cn.tendata.crm.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by Luo Min on 2017/1/20.
 */
@Controller
public class MessageController {


    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @Autowired
    public MessageController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/message")
    public void message(Principal principal, String message){
       User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        ChatMessage chatMessage =JsonUtils.deserialize(message,ChatMessage.class);
        chatMessage.setFromUser(user.getUsername());
        chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getToUser(),"/queue/notifications",chatMessage);
    }
}

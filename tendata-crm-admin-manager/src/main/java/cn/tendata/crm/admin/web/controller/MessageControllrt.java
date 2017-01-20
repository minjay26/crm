package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.data.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Luo Min on 2017/1/20.
 */
@Controller
@RequestMapping("/admin")
public class MessageControllrt {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    public void message(@CurrentUser User user,User approver){
       messagingTemplate.convertAndSendToUser(approver.getUsername(),"/user/queue/notifications",user.getUsername()+" test message");
    }
}

package cn.tendata.crm.message;

import cn.tendata.crm.data.domain.GoOutRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Created by minjay on 2017/2/23.
 */
public class MessageProcessGateway implements MessageProcess {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageProcessGateway(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void apply(GoOutRecord record) {
        messagingTemplate.convertAndSendToUser(record.getMatterRecord().getApprover().getUsername(),"/queue/notifications",record);
    }
}

package cn.tendata.crm.data.mongodb.domain;

import cn.tendata.crm.data.domain.AbstractEntityAuditable;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by minjay on 2017/2/17.
 */
@Document
public class ChatMessage extends AbstractEntityAuditable<String> {

    private String toUser;

    private String fromUser;

    private String content;

    @Id
    public String getId() {
        return super.getId();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package cn.tendata.crm.data.domain;

import cn.tendata.crm.data.EmailSource;

import javax.persistence.*;

/**
 * Created by Luo Min on 2016/12/6.
 */
@Entity
public class Email extends AbstractEntityAuditable<Long>{

    private static final long serialVersionUID = 1L;

    private User fromUser;

    private User toUser;

    private boolean deleted;

    private String content;

    private EmailSource source;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @OneToOne
    @JoinColumn(name = "from_user_id")
    public User getFromUser() {
        return fromUser;
    }


    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    @Column(name = "to_user_id",nullable = false)
    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Lob
    @Column(name = "content",nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "source",nullable = false)
    public EmailSource getSource() {
        return source;
    }

    public void setSource(EmailSource source) {
        this.source = source;
    }
}

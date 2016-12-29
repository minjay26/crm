package cn.tendata.crm.data.domain;

import cn.tendata.crm.data.EmailSource;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Luo Min on 2016/12/6.
 */
@Entity
public class Mail extends AbstractEntityAuditable<Long>{

    private static final long serialVersionUID = 1L;

    private User fromUser;

    private User toUser;

    private boolean deleted;

    private String title;

    private String content;

    private EmailSource source;

    private Collection<MailAttachment> attachments=Collections.emptyList();

    private boolean readed;

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

    @OneToOne
    @JoinColumn(name = "to_user_id")
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

    @NotNull
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
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

    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY,mappedBy = "mail")
    public Collection<MailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<MailAttachment> attachments) {
        this.attachments = attachments;
    }

    @Column(name = "readed")
    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}

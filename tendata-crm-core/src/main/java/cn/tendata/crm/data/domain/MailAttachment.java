package cn.tendata.crm.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Luo Min on 2016/12/7.
 */
@Entity
public class MailAttachment extends AbstractEntityAuditable<Long>{

    private String name;

    private String size;

    private String url;

    @JsonIgnore
    private Mail mail;

    private User user;

    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "size",nullable = false)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "url",nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "mail_id")
    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

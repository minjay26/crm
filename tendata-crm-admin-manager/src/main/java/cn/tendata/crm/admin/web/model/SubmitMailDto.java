package cn.tendata.crm.admin.web.model;

import cn.tendata.crm.data.domain.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Luo Min on 2017/1/3.
 */
public class SubmitMailDto {

    private long fromMailId;

    @NotNull
    private String content;

    private int toUser;

    @NotNull
    @Size(min = 1)
    private String title;

    public SubmitMailDto() {

    }

    public SubmitMailDto(String content, int toUser, String title) {
        this.content = content;
        this.toUser = toUser;
        this.title = title;
    }

    public SubmitMailDto(int fromMailId, String content, int toUser, String title) {
        this.fromMailId = fromMailId;
        this.content = content;
        this.toUser = toUser;
        this.title = title;
    }

    public long getFromMailId() {
        return fromMailId;
    }

    public void setFromMailId(long fromMailId) {
        this.fromMailId = fromMailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }
}

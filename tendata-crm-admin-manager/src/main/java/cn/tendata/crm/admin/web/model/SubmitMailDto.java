package cn.tendata.crm.admin.web.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Luo Min on 2017/1/5.
 */
public class SubmitMailDto {

    @NotNull
    private String toUser;

    @NotNull
    private String content;

    @NotNull
    private String title;

    public SubmitMailDto() {
    }

    public SubmitMailDto(String toUser, String content, String title) {
        this.toUser = toUser;
        this.content = content;
        this.title = title;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
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
}

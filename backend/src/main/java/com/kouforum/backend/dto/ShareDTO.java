package com.kouforum.backend.dto;

import java.util.Date;

import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;

public class ShareDTO {
    private long id;
    private String content;
    private Date date;
    private User user;

    public ShareDTO(Share share) {
        setId(share.getId());
        setContent(share.getContent());
        setDate(share.getDate());
        setUser(share.getUser());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

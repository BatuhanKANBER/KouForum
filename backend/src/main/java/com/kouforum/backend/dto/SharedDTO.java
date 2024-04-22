package com.kouforum.backend.dto;

import java.util.Date;

import com.kouforum.backend.models.Shared;

public class SharedDTO {
    private long id;
    private String content;
    private Date date;

    public SharedDTO(Shared shared) {
        setId(shared.getId());
        setContent(shared.getContent());
        setDate(shared.getDate());
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

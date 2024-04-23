package com.kouforum.backend.dto;

import java.util.Date;

import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;

public class ShareDTO {
    private long id;
    private String content;
    private Date date;
    private User user;
    private FileAttachmentDTO fileAttachmentDTO;

    public ShareDTO(Share share) {
        this.setId(share.getId());
        this.setContent(share.getContent());
        this.setDate(share.getDate());
        this.setUser(share.getUser());
        if (share.getFileAttachment() != null) {
            this.fileAttachmentDTO = new FileAttachmentDTO(share.getFileAttachment());
        }
    }

    public FileAttachmentDTO getFileAttachmentDTO() {
        return fileAttachmentDTO;
    }

    public void setFileAttachmentDTO(FileAttachmentDTO fileAttachmentDTO) {
        this.fileAttachmentDTO = fileAttachmentDTO;
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

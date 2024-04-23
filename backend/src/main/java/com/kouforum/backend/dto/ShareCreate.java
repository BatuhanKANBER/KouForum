package com.kouforum.backend.dto;

import jakarta.validation.constraints.Size;

public class ShareCreate {
    @Size(min = 1, max = 500)
    private String content;
    
    private long attachmentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }
}

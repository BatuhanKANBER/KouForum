package com.kouforum.backend.dto;

import com.kouforum.backend.models.FileAttachment;

public class FileAttachmentDTO {
    private String name;

    public FileAttachmentDTO(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

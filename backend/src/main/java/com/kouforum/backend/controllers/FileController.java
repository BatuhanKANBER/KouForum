package com.kouforum.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kouforum.backend.models.FileAttachment;
import com.kouforum.backend.services.FileService;

@RestController
@RequestMapping("/api/attachments")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    FileAttachment saveKouforumAttachments(MultipartFile file) {
        return fileService.saveKouforumAttachments(file);
    }
}

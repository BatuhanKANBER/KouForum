package com.kouforum.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kouforum.backend.models.FileAttachment;
import com.kouforum.backend.repositories.FileAttachmentRepository;

@Service
@EnableScheduling
public class FileCleanUpService {
    @Autowired
    FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    FileService fileService;

    @Scheduled(fixedRate = 60 * 1000)
    public void cleanUpStorage() {
        Date twentyFourHours = new Date(System.currentTimeMillis() - (60 * 1000));
        List<FileAttachment> filesToBeDeleted = fileAttachmentRepository
                .findByDateBeforeAndShareIsNull(twentyFourHours);
        for (FileAttachment file : filesToBeDeleted) {
            fileService.deleteProfileImage(file.getName());
            fileAttachmentRepository.deleteById(file.getId());
        }
    }
}

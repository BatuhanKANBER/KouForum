package com.kouforum.backend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kouforum.backend.models.FileAttachment;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
    List<FileAttachment> findByDateBeforeAndShareIsNull(Date date);

}

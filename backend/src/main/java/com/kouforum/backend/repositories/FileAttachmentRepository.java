package com.kouforum.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kouforum.backend.models.FileAttachment;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {

}

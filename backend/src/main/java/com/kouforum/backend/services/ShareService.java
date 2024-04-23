package com.kouforum.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.CurrentUser;
import com.kouforum.backend.dto.ShareCreate;
import com.kouforum.backend.models.FileAttachment;
import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.FileAttachmentRepository;
import com.kouforum.backend.repositories.ShareRepository;

@Service
public class ShareService {
    @Autowired
    ShareRepository shareRepository;

    @Autowired
    UserService userService;

    @Autowired
    FileAttachmentRepository fileAttachmentRepository;

    public void save(ShareCreate shareCreate, CurrentUser currentUser) {
        Share share = new Share();
        share.setContent(shareCreate.getContent());
        User inDB = userService.getUser(currentUser.getId());
        share.setDate(new Date());
        share.setUser(inDB);
        shareRepository.save(share);
        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository
                .findById(shareCreate.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setShare(share);
            fileAttachmentRepository.save(fileAttachment);
        }
    }

    public Page<Share> getShares(Pageable page) {
        return shareRepository.findAll(page);
    }

    public Page<Share> getSharesOfUser(Long id, Pageable page) {
        User inDB = userService.getUser(id);
        return shareRepository.findByUser(inDB, page);
    }

    public Page<Share> getOldShares(Long id, Pageable page) {
        return shareRepository.findByIdLessThan(id, page);
    }

    public Page<Share> getOldSharesOfUser(Long sharesId, Long id, Pageable page) {
        User inDB = userService.getUser(id);
        return shareRepository.findByIdLessThanAndUser(sharesId, inDB, page);
    }

    public long getNewSharesCount(Long id) {
        return shareRepository.countByIdGreaterThan(id);
    }

    public long getNewSharesCountOfUser(Long id, Long sharesId) {
        User inDB = userService.getUser(id);
        return shareRepository.countByIdGreaterThanAndUser(sharesId, inDB);
    }

    public List<Share> getNewShares(Long id, Sort sort) {
        return shareRepository.findByIdGreaterThan(id, sort);
    }

    public List<Share> getNewSharesForUser(Long sharesId, Long id, Sort sort) {
        User inDB = userService.getUser(id);
        return shareRepository.findByIdGreaterThanAndUser(sharesId, inDB, sort);
    }

}

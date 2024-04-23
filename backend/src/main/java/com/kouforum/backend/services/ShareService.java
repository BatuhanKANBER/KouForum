package com.kouforum.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.CurrentUser;
import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.ShareRepository;

@Service
public class ShareService {
    @Autowired
    ShareRepository shareRepository;

    @Autowired
    UserService userService;

    public void save(Share share, CurrentUser currentUser) {
        User user = new User();
        user.setId(currentUser.getId());
        share.setDate(new Date());
        share.setUser(user);
        shareRepository.save(share);
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

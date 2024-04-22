package com.kouforum.backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kouforum.backend.models.CurrentUser;
import com.kouforum.backend.models.Shared;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.SharedRepository;

@Service
public class SharedService {
    @Autowired
    SharedRepository sharedRepository;

    public void save(Shared shared, CurrentUser currentUser) {
        User user = new User();
        user.setId(currentUser.getId());
        shared.setDate(new Date());
        shared.setUser(user);
        sharedRepository.save(shared);
    }

    public Page<Shared> getShareds(Pageable page) {
        return sharedRepository.findAll(page);
    }
}

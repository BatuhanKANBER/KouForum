package com.kouforum.backend.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouforum.backend.models.Shared;
import com.kouforum.backend.repositories.SharedRepository;

@Service
public class SharedService {
    @Autowired
    SharedRepository sharedRepository;

    public void save(Shared shared) {
        shared.setDate(new Date(System.currentTimeMillis()));
        sharedRepository.save(shared);
    }
}

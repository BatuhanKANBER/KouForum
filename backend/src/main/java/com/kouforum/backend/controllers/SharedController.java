package com.kouforum.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.models.Shared;
import com.kouforum.backend.services.SharedService;
import com.kouforum.backend.shared.GenericMessage;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/shareds")
public class SharedController {
    @Autowired
    SharedService sharedService;

    @PostMapping("/submit")
    GenericMessage saveShared(@Valid @RequestBody Shared shared) {
        sharedService.save(shared);
        return new GenericMessage("Shared is created.");
    }
}

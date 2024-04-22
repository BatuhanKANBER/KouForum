package com.kouforum.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.SharedDTO;
import com.kouforum.backend.models.CurrentUser;
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
    GenericMessage saveShared(@Valid @RequestBody Shared shared,
            @AuthenticationPrincipal CurrentUser currentUser) {
        sharedService.save(shared, currentUser);
        return new GenericMessage("Shared is created.");
    }

    @GetMapping("/list")
    Page<SharedDTO> getShareds(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return sharedService.getShareds(page).map(SharedDTO::new);
    }
}

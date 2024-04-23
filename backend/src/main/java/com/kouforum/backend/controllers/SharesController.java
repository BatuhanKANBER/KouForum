package com.kouforum.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.CurrentUser;
import com.kouforum.backend.dto.ShareCreate;
import com.kouforum.backend.dto.ShareDTO;
import com.kouforum.backend.services.ShareService;
import com.kouforum.backend.shared.GenericMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/shares")
public class SharesController {
    @Autowired
    ShareService shareService;

    @PostMapping("/submit")
    GenericMessage saveShared(@Valid @RequestBody ShareCreate share,
            @AuthenticationPrincipal CurrentUser currentUser) {
        shareService.save(share, currentUser);
        return new GenericMessage("Shared is created.");
    }

    @GetMapping("/list")
    Page<ShareDTO> getShareds(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return shareService.getShares(page).map(ShareDTO::new);
    }

    @GetMapping("/list/{id:[0-9]+}")
    ResponseEntity<?> getSharedsRelative(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page,
            @PathVariable Long id,
            @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
            @RequestParam(name = "direction", defaultValue = "before") String direction) {
        if (count) {
            long newShareCount = shareService.getNewSharesCount(id);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newShareCount);
            return ResponseEntity.ok(response);
        }
        if (direction.equals("after")) {
            List<ShareDTO> newShares = shareService.getNewShares(id, page.getSort()).stream().map(ShareDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(newShares);
        }
        return ResponseEntity.ok(shareService.getOldShares(id, page).map(ShareDTO::new));
    }

    @GetMapping("/user/{id}/list")
    Page<ShareDTO> getSharesOfUser(@PathVariable Long id,
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return shareService.getSharesOfUser(id, page).map(ShareDTO::new);
    }

    @GetMapping("/user/{id}/list/{sharesId:[0-9]+}")
    ResponseEntity<?> getSharesOfUserRelative(@PathVariable Long id, @PathVariable Long sharesId,
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable page,
            @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
            @RequestParam(name = "direction", defaultValue = "before") String direction) {
        if (count) {
            long newShareCount = shareService.getNewSharesCountOfUser(id, sharesId);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newShareCount);
            return ResponseEntity.ok(response);
        }
        if (direction.equals("after")) {
            List<ShareDTO> newShares = shareService.getNewSharesForUser(sharesId, id, page.getSort()).stream()
                    .map(ShareDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(newShares);
        }
        return ResponseEntity.ok(shareService.getOldSharesOfUser(sharesId, id, page).map(ShareDTO::new));
    }

    @DeleteMapping("/{id}")
    GenericMessage deleteShare(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        shareService.delete(id, currentUser);
        return new GenericMessage("Share is removed.");
    }

}

package com.kouforum.backend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;

public interface ShareRepository extends JpaRepository<Share, Long>, JpaSpecificationExecutor<Share> {
    Page<Share> findByUser(User user, Pageable page);

    Page<Share> findByIdLessThan(Long id, Pageable page);

    Page<Share> findByIdLessThanAndUser(Long shareId, User user, Pageable page);

    long countByIdGreaterThan(long id);

    Long countByIdGreaterThanAndUser(long id, User user);

    List<Share> findByIdGreaterThan(long id, Sort sort);

    List<Share> findByIdGreaterThanAndUser(long id, User user, Sort sort);
}

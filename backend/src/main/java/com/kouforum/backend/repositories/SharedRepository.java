package com.kouforum.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kouforum.backend.models.Shared;

public interface SharedRepository extends JpaRepository<Shared, Long>, JpaSpecificationExecutor<Shared> {
    
}

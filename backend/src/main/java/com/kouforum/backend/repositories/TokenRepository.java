package com.kouforum.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kouforum.backend.models.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

}

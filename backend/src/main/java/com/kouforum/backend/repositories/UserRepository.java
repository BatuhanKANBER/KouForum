package com.kouforum.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kouforum.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
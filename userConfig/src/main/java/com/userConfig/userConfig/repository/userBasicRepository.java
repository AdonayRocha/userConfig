package com.userConfig.userConfig.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userConfig.userConfig.model.UserBasic;

public interface UserBasicRepository extends JpaRepository<UserBasic, Long> {
    Optional<UserBasic> findByEmail(String email);
}

package com.userConfig.userConfig.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userConfig.userConfig.model.userBasic;

public interface userBasicRepository extends JpaRepository<userBasic, Long> {
    Optional<userBasic> findByEmail(String email);
}

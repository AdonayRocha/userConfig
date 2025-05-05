package com.userConfig.repository;

import com.userConfig.model.UserBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBasicRepository extends JpaRepository<UserBasic, Long> {
    Optional<UserBasic> findByEmail(String email);
}

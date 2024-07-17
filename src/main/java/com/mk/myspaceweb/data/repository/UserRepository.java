package com.mk.myspaceweb.data.repository;

import com.mk.myspaceweb.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
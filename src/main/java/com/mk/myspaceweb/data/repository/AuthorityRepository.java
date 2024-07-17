package com.mk.myspaceweb.data.repository;

import com.mk.myspaceweb.data.entity.Authority;
import com.mk.myspaceweb.data.entity.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}

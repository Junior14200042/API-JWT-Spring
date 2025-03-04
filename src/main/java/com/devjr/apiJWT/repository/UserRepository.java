package com.devjr.apiJWT.repository;

import com.devjr.apiJWT.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findUserEntityByUsername(String username);

}

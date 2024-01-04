package com.app100.capitalinvest.repository;

import java.util.UUID;
import com.app100.capitalinvest.entity.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
}

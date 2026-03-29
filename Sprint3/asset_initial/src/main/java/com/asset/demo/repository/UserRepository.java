package com.asset.demo.repository;

import com.asset.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email=?1")
    User getUserByUsername(String username);
}

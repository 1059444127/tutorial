package com.kingmed.ccl.gps.wx.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingmed.ccl.gps.wx.model.security.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.tradlinx.backendtest.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.tradlinx.backendtest.entity.User;

public interface UserService {

	User save(User user);

	UserDetails loadUserBySeq(long seq);

	User findByUserid(String userid);

	User findBySeq(long seq);

}
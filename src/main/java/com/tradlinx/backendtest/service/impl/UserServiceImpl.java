package com.tradlinx.backendtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.repository.UserRepository;
import com.tradlinx.backendtest.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserBySeq(long seq) {
		// TODO Auto-generated method stub
		return userRepository.findById(seq);
	}

	@Override
	public User findByUserid(String userid) {
		// TODO Auto-generated method stub
		return userRepository.findByUserid(userid);
	}

	@Override
	public User findBySeq(long seq) {
		// TODO Auto-generated method stub
		return userRepository.findBySeq(seq);
	}

}

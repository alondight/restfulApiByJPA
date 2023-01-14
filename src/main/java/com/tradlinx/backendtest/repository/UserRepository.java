package com.tradlinx.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.tradlinx.backendtest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUseridAndPw(String userid, String pw);

	UserDetails findById(long seq);

	User findByUserid(String userid);

	User findBySeq(long seq);

}
package com.cloudcompilerr.development.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.entity.UserEntity;
import com.cloudcompilerr.development.repository.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDetailsRepository userDetailsRepository;

	@Autowired
	public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
	}

	@Override
	public String addUser(UserDetails userDetails) {
		UserEntity userEntity = UserEntity.builder().gender(userDetails.getGender().name())
				.userName(userDetails.getUserName()).build();
		UserEntity userAdded = userDetailsRepository.save(userEntity);
		return userAdded.getUserId().toString();
	}

}

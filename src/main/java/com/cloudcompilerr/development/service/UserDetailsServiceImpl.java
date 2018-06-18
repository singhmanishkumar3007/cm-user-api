package com.cloudcompilerr.development.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cloudcompilerr.development.controllers.PaginatedResult;
import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.entity.UserEntity;
import com.cloudcompilerr.development.repository.UserDetailsRepository;
import com.cloudcompilerr.development.transformers.UserDetailsToUserEntityTransformer;
import com.cloudcompilerr.development.transformers.UserEntityToUserDetailsTransformer;

@Service
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

	@Override
	public Map<Integer, String> addMultipleUsers(List<UserDetails> userDetails) {
		List<UserEntity> userEntities = new UserDetailsToUserEntityTransformer().apply(userDetails);
		List<UserEntity> userEntitiesAdded = (List<UserEntity>) userDetailsRepository.saveAll(userEntities);
		Map<Integer, String> addedUsersMap = userEntitiesAdded.stream()
				.collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getUserName));
		return addedUsersMap;
	}

	@SuppressWarnings("deprecation")
	@Override
	public PaginatedResult<UserDetails> findUsers(Integer page, Integer perPage) {

		Pageable pageable = new PageRequest(page, perPage);
		Page<UserEntity> userEntitiesInPage = userDetailsRepository.findAll(pageable);
		Long totalNumberOfPages = userEntitiesInPage.getTotalElements();
		List<UserDetails> userDetails = new UserEntityToUserDetailsTransformer().apply(userEntitiesInPage.getContent());
		PaginatedResult<UserDetails> paginatedUsers = new PaginatedResult<>(totalNumberOfPages, userDetails);
		return paginatedUsers;
	}

}

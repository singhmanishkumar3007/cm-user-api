package com.cloudcompilerr.development.service;

import java.util.List;
import java.util.Map;

import com.cloudcompilerr.development.controllers.PaginatedResult;
import com.cloudcompilerr.development.domain.UserDetails;

public interface UserDetailsService {

	String addUser(UserDetails userDetails);

	Map<Integer, String> addMultipleUsers(List<UserDetails> userDetails);

	PaginatedResult<UserDetails> findUsers(Integer page, Integer perPage);

}

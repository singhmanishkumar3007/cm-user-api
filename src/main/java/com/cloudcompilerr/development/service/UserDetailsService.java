package com.cloudcompilerr.development.service;

import java.util.List;
import java.util.Map;

import com.cloudcompilerr.development.domain.Gender;
import com.cloudcompilerr.development.domain.PaginatedResult;
import com.cloudcompilerr.development.domain.UserDetails;

public interface UserDetailsService {

    String addUser(UserDetails userDetails);

    Map<Integer, String> addMultipleUsers(List<UserDetails> userDetails);

    PaginatedResult<UserDetails> findUsers(Integer page, Integer perPage);

    List<UserDetails> findUsersByMatchingNameAndGender(String name, Gender gender);

    UserDetails findUserById(String id);

}

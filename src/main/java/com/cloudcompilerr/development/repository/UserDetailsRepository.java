package com.cloudcompilerr.development.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudcompilerr.development.entity.UserEntity;

public interface UserDetailsRepository extends PagingAndSortingRepository<UserEntity, Integer> {

}

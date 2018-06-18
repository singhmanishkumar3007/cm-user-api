package com.cloudcompilerr.development.repository;

import org.springframework.data.repository.CrudRepository;

import com.cloudcompilerr.development.entity.UserEntity;

public interface UserDetailsRepository extends CrudRepository<UserEntity, Integer> {

}

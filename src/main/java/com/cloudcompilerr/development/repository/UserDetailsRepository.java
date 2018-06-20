package com.cloudcompilerr.development.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cloudcompilerr.development.entity.UserEntity;

public interface UserDetailsRepository extends PagingAndSortingRepository<UserEntity, Integer> {

    String QUERY_BY_MATCH_NAME = "SELECT userEntity FROM UserEntity userEntity WHERE userEntity.userName like %:name% AND userEntity.gender=:gender";

    @Query(QUERY_BY_MATCH_NAME)
    List<UserEntity> getUsersByMatchingNameAndGender(@Param("name") String name, @Param("gender") String gender);

}

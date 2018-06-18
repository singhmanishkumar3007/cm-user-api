package com.cloudcompilerr.development.transformers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cloudcompilerr.development.domain.Gender;
import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.entity.UserEntity;

public class UserEntityToUserDetailsTransformer implements Function<List<UserEntity>, List<UserDetails>> {

	@Override
	public List<UserDetails> apply(List<UserEntity> userEntity) {
		List<UserDetails> userDetails = userEntity.stream().map(userElement -> transformToUserDetails(userElement))
				.collect(Collectors.toList());
		return userDetails;
	}

	private UserDetails transformToUserDetails(UserEntity userEntity) {
		UserDetails userDetails = UserDetails.builder().gender(Gender.valueOf(userEntity.getGender()))
				.userId(userEntity.getUserId().toString()).userName(userEntity.getUserName()).build();
		return userDetails;
	}

}

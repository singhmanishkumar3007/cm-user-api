package com.cloudcompilerr.development.transformers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.entity.UserEntity;

public class UserDetailsToUserEntityTransformer implements Function<List<UserDetails>, List<UserEntity>> {

    @Override
    public List<UserEntity> apply(List<UserDetails> userDetails) {
        List<UserEntity> userEntities = userDetails.stream().map(userDetail -> transformToUserEntity(userDetail))
                .collect(Collectors.toList());
        return userEntities;
    }

    private UserEntity transformToUserEntity(UserDetails userDetail) {
        UserEntity userEntity = UserEntity.builder().gender(userDetail.getGender().name())
                .userName(userDetail.getUserName()).build();
        return userEntity;
    }

}

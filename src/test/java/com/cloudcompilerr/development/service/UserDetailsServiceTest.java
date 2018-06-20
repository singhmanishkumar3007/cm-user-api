package com.cloudcompilerr.development.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.entity.UserEntity;
import com.cloudcompilerr.development.repository.UserDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserDetailsRepository userDetailsRepositoryMock;

    private String id;

    private Optional<UserEntity> value;

    @Before
    public void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userDetailsRepositoryMock);
        id = "1";
        value = Optional.of(new UserEntity(1, "manish", "MALE"));
    }

    @Test
    public void testFindUserById() {
        Mockito.when(userDetailsRepositoryMock.findById(Integer.parseInt(id))).thenReturn(value);
        UserDetails actualUserDetails = userDetailsService.findUserById(id);
        assertEquals("1", actualUserDetails.getUserId());

    }

}

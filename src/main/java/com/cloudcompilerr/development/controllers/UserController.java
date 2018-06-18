package com.cloudcompilerr.development.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.service.UserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	private final UserDetailsService userDetailsService;

	@Autowired
	public UserController(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@PostMapping(value = "/add_user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUsers(@RequestBody UserDetails userDetails) {

		return new ResponseEntity<String>(userDetailsService.addUser(userDetails), HttpStatus.CREATED);
	}

}

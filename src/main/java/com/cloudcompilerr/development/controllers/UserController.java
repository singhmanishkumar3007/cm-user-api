package com.cloudcompilerr.development.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.service.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		Logger.info("adding single user {}", userDetails);
		return new ResponseEntity<String>(userDetailsService.addUser(userDetails), HttpStatus.CREATED);
	}

	@PostMapping(value = "/add_user/multiple", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, String>> addMultipleUsers(@RequestBody List<UserDetails> userDetails)
			throws Exception {

		Logger.info("list of users to be added is {}", new ObjectMapper().writeValueAsString(userDetails));
		return new ResponseEntity<Map<Integer, String>>(userDetailsService.addMultipleUsers(userDetails),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/all_users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaginatedResult<UserDetails>> getAllUsers(@RequestParam("page") Integer page,
			@RequestParam("per_page") Integer perPage) throws Exception {

		PaginatedResult<UserDetails> paginatedResult = userDetailsService.findUsers(page, perPage);

		return new ResponseEntity<PaginatedResult<UserDetails>>(paginatedResult, HttpStatus.OK);
	}

}

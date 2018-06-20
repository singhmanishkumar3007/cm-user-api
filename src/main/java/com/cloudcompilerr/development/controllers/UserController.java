package com.cloudcompilerr.development.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.development.domain.Gender;
import com.cloudcompilerr.development.domain.PaginatedResult;
import com.cloudcompilerr.development.domain.UserDetails;
import com.cloudcompilerr.development.exception.StandardError;
import com.cloudcompilerr.development.exception.StandardErrorCode;
import com.cloudcompilerr.development.exception.StandardException;
import com.cloudcompilerr.development.service.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

    private final UserDetailsService userDetailsService;

    private final Validator validator;

    @Autowired
    public UserController(UserDetailsService userDetailsService,
            @Qualifier("userRequestValidator") Validator validator) {
        this.userDetailsService = userDetailsService;
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping(value = "/add_user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUsers(@Validated @RequestBody UserDetails userDetails, BindingResult bindingResult)
            throws Exception {

        if (bindingResult.hasErrors()) {
            Logger.error("Validation failure for Request Body");
            List<String[]> objectErrors = bindingResult.getAllErrors().stream()
                    .map(bindingError -> bindingError.getCodes()).collect(Collectors.toList());
            String errors = objectErrors.stream().map(objectErrorArray -> String.join(",", objectErrorArray))
                    .collect(Collectors.joining());
            StandardError standardError = StandardError.builder().method("addusers").field("requestbody")
                    .message(errors).build();
            StandardException standardException = new StandardException(HttpStatus.BAD_REQUEST,
                    Arrays.asList(standardError), StandardErrorCode.SC400, new Throwable("Bad Message request"));
            throw standardException;
        }

        Logger.info("adding single user {}", userDetails);
        return new ResponseEntity<String>(userDetailsService.addUser(userDetails), HttpStatus.CREATED);
    }

    @PostMapping(value = "/add_user/multiple",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, String>> addMultipleUsers(@RequestBody List<UserDetails> userDetails)
            throws Exception {

        Logger.info("list of users to be added is {}", new ObjectMapper().writeValueAsString(userDetails));
        return new ResponseEntity<Map<Integer, String>>(userDetailsService.addMultipleUsers(userDetails),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/all_users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginatedResult<UserDetails>> getAllUsers(@RequestParam(value = "page",
            required = false,
            defaultValue = "0") Integer page,
            @RequestParam(value = "per_page",
                    required = false,
                    defaultValue = "5") Integer perPage)
            throws Exception {

        PaginatedResult<UserDetails> paginatedResult = userDetailsService.findUsers(page, perPage);

        return new ResponseEntity<PaginatedResult<UserDetails>>(paginatedResult, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails getUserById(@PathVariable("id") String userId) throws Exception {

        return userDetailsService.findUserById(userId);
    }

    @GetMapping(value = "/users/{name}/gender/{gender}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDetails> getUsersByMatchingNameAndgender(@PathVariable("name") String userNameLike,
            @PathVariable("gender") Gender gender) throws Exception {

        return userDetailsService.findUsersByMatchingNameAndGender(userNameLike, gender);
    }

}

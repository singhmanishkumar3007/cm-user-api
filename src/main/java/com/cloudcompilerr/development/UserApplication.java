package com.cloudcompilerr.development;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloudcompilerr.annotation.Traceable;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UserApplication {

	@Traceable
	public static void main(String[] args) {
		Logger.info("Hello User");
		SpringApplication.run(UserApplication.class, args);
	}
}

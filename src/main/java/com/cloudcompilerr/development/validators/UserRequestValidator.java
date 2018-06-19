package com.cloudcompilerr.development.validators;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cloudcompilerr.development.domain.UserDetails;

@Component("userRequestValidator")
public class UserRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (UserDetails.class.equals(clazz) || ArrayList.class.equals(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {

		
		UserDetails userDetails = (UserDetails) target;

		if (!StringUtils.isEmpty(userDetails.getUserName())) {
			if (userDetails.getUserName().matches(".*[a-zA-Z0-9_]+.*")) {
				errors.rejectValue("userName", "invalid characters", new Object[] { "'userName'" },
						"user name can contain only alphabets or digits");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "gender is required");
	}

}

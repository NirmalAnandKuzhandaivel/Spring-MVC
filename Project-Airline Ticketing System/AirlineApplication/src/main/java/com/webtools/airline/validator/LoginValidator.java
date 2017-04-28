package com.webtools.airline.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webtools.airline.model.LoginBean;



	
	@Component
	public class LoginValidator implements Validator{

		@Override
		public boolean supports(Class userClass) {
			// TODO Auto-generated method stub
			return userClass.equals(LoginBean.class);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			LoginBean c = (LoginBean) obj;
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "Email id required");
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "error.invalid.user", "Password Required");
			
		}

	}


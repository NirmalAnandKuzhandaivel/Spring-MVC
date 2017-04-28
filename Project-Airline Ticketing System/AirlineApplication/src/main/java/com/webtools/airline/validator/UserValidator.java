package com.webtools.airline.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.webtools.airline.model.User;

import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator implements Validator{
	
	private static final String STRING_PATTERN="([a-zA-Z]+)";
	private static final String EMAIL_PATTERN="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	private Pattern FIRST_NAME_STRING_PATTERN_COMPILE;
	private Pattern LAST_NAME_STRING_PATTERN_COMPILE;
	private Pattern EMAIL_PATTERN_COMPILE;
	private Pattern PASSWORD_PATTERN_COMPILE;
	private Matcher FIRST_STRING_MATCHER;
	private Matcher LAST_STRING_MATCHER;
	private Matcher EMAIL_MATCHER;
	private Matcher PASSWORD_MATCHER;

	@Override
	public boolean supports(Class userClass) {
				return userClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		FIRST_NAME_STRING_PATTERN_COMPILE=Pattern.compile(STRING_PATTERN);
		LAST_NAME_STRING_PATTERN_COMPILE=Pattern.compile(STRING_PATTERN);
		EMAIL_PATTERN_COMPILE=Pattern.compile(EMAIL_PATTERN);
		PASSWORD_PATTERN_COMPILE=Pattern.compile(PASSWORD_PATTERN);
		
		    User c = (User) obj;
	        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstname", "First Name Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastname", "Last Name Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailID", "error.invalid.email", "Email ID Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleType", "error.invalid.Role", "Select Role");*/
	        
	        FIRST_STRING_MATCHER=FIRST_NAME_STRING_PATTERN_COMPILE.matcher(c.getFirstName());
	        if(!FIRST_STRING_MATCHER.matches()) {
	             errors.rejectValue("firstName","error.invalid.user","Enter only Strings");
	        }
	        
	        LAST_STRING_MATCHER=LAST_NAME_STRING_PATTERN_COMPILE.matcher(c.getLastName());
	        if(!LAST_STRING_MATCHER.matches()) {
	             errors.rejectValue("lastName","error.invalid.firstname","Enter only Strings");
	        }
	        
	        EMAIL_MATCHER=EMAIL_PATTERN_COMPILE.matcher(c.getEmailID());
	        if(!EMAIL_MATCHER.matches()) {
	             errors.rejectValue("emailID","error.invalid.email","Enter only Valid Email Format");
	        }
	        
	        PASSWORD_MATCHER=PASSWORD_PATTERN_COMPILE.matcher(c.getPassword());
	        if(!PASSWORD_MATCHER.matches()) {
	             errors.rejectValue("password","error.invalid.password","Enter only Valid Password 6-20 Characters");
	        }
	}

}

package com.isaacsuntag.loginreg.auth.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.isaacsuntag.loginreg.models.User;
import com.isaacsuntag.loginreg.services.UserService;

@Component
public class UserValidator implements Validator {
	private UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        
        if (!user.getPassword_confirm().equals(user.getPassword())) {
            // 3
            errors.rejectValue("password_confirm", "Match");
        }      
        
        if(userService.findByEmail(user.getEmail()) != null) {
        		errors.rejectValue("email", "Taken");
        }
    }

}
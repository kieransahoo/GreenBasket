package com.masai.Controller;

import com.masai.Model.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Services.UserLoginService;





@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserLoginService userService;
	
	@PostMapping("/userlogin")
    public ResponseEntity<CurrentUserSession> logInUserHandler(@RequestBody User user) throws UserException {

        CurrentUserSession cs = userService.logIntoAccount(user);

        return new ResponseEntity<>(cs, HttpStatus.OK);

    }
	
	@PostMapping("/userlogout")
    public String logoutUserHandler(@RequestParam(required = false) String key) throws UserException {
        
		return userService.logOutFromAccount(key);

    }

}

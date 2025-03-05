package com.masai.Services;

import com.masai.Exception.UserException;
import com.masai.Model.CurrentUserSession;
import com.masai.Model.User;

public interface UserLoginService {
	
	public CurrentUserSession logIntoAccount(User user) throws UserException;

    public String logOutFromAccount(String key) throws UserException;

}

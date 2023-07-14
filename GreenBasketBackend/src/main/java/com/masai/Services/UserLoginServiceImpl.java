package com.masai.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.ThresholdingOutputStream;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.CurrentUserSession;
import com.masai.Model.Customer;
import com.masai.Model.User;
import com.masai.Repository.AdminDao;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;


@Service
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	

	@Override
	public CurrentUserSession logIntoAccount(User user) throws UserException {

		Admin existingAdmin = adminDao.findByEmailId(user.getEmailId());

		if(existingAdmin == null) {

			Customer existingCustomer = customerDao.findByEmailId(user.getEmailId());

			if(existingCustomer == null) {
				throw new UserException("please enter valid email address.");
			}

			Optional<CurrentUserSession> validCustomerSessionOpt = userSessionDao.findById(existingCustomer.getCustomerId());

			if(validCustomerSessionOpt.isPresent()) {

				throw new UserException("User already logged In with this email");
			}

			if(existingCustomer.getPassword().equals(user.getPassword())) {

				String key = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),key,LocalDateTime.now(),existingCustomer.getRole());

				CurrentUserSession cs=userSessionDao.save(currentUserSession);

				return cs;

			}
			else
				throw new UserException("please enter valid password");
		}

		else {

			Optional<CurrentUserSession> validCustomerSessionOpt = userSessionDao.findById(existingAdmin.getAdminId());

			if(validCustomerSessionOpt.isPresent()) {

				throw new UserException("User already logged In with this email");
			}

			if(existingAdmin.getPassword().equals(user.getPassword())) {

				String key = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(existingAdmin.getAdminId(),key,LocalDateTime.now(),existingAdmin.getRole());

				CurrentUserSession cs=userSessionDao.save(currentUserSession);

				return cs;

			}
			else
				throw new UserException("please enter valid password");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws UserException {
		
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("User not logged In");
		}
		
		userSessionDao.delete(currentUserSession);
		
		
		return "User Successfully Logged Out";
	}

}

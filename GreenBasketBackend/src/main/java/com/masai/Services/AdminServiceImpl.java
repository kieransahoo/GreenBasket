package com.masai.Services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.CurrentUserSession;
import com.masai.Repository.AdminDao;
import com.masai.Repository.UserSessionDao;




@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public Admin addAdmin(Admin admin) throws AdminException {
		if(admin!=null ){
			Admin getAdmin=adminDao.findByEmailId(admin.getEmailId());
			if(getAdmin==null){
				return adminDao.save(admin);
			}

		}
		throw new AdminException("admin already present with this emailId");
	}

	@Override
	public Admin viewAdmin(Integer admin_id,String key) throws AdminException,UserException {
		
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		if(currentUserSession.getRole()==1) {


			Admin admin = adminDao.findById(admin_id).orElseThrow(() -> new AdminException("Admin not exists with Id" + admin_id));

			return admin;
		}
		throw new AdminException("something went wrong");

	}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException ,UserException{
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		Admin updatedadmin = adminDao.findByEmailId(admin.getEmailId());
		
		if(updatedadmin.equals(null)) {
			throw new AdminException("No Admin available with "+admin.getEmailId());
		}
		
		adminDao.save(updatedadmin);
		
		return updatedadmin;
	}

	@Override
	public Admin removeAdmin(Integer admin_id, String key) throws AdminException,UserException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		Optional<Admin> optional = adminDao.findById(admin_id);

		if(optional.get()==null){
			throw new AdminException("admin not found");
		}

		adminDao.delete(optional.get());
		
		return optional.get();
		
	}

}

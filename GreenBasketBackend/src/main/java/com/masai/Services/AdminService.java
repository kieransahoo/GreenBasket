package com.masai.Services;

import java.util.List;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Model.Admin;



public interface AdminService {
	
	
	public Admin addAdmin(Admin admin) throws AdminException;
	
	public Admin viewAdmin(Integer admin_id,String key) throws AdminException,UserException;
	
	public Admin updateAdmin( Admin admin,String key) throws AdminException,UserException;
	
	public Admin removeAdmin(Integer admin_id,String key)throws AdminException,UserException;

}

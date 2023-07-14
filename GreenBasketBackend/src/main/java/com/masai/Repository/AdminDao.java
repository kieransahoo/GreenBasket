package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Admin;



@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	
	public Admin findByEmailId(String emailId);
	
	public Admin findByEmailIdAndPassword(String emailId,String password);

}

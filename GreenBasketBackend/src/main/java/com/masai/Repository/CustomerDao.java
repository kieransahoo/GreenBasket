package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Customer;




@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{

	public Customer findByEmailId(String emailId);
	
	public Customer findByEmailIdAndPassword(String emailId,String password);
	
	public Customer findByEmailIdAndMobileNumber(String emailId,String phone);


}

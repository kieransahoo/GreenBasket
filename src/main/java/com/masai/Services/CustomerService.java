package com.masai.Services;


import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import com.masai.Model.Customer;



public interface CustomerService {
	
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer viewCustomer(Integer customerId,String key)throws CustomerException,UserException;

	public List<Customer> viewCustomerList(String key)throws CustomerException;
	
	public Customer updateCustomer(Customer customer,String key)throws CustomerException,UserException;
	
	public Customer removeCustomer(Integer customerId,String key) throws CustomerException,UserException;
	
	public Customer updatePassword(String email,String phone,String password) throws CustomerException,UserException;

}

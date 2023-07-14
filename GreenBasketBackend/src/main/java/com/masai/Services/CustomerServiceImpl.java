package com.masai.Services;

import java.util.List;
import java.util.Optional;

import com.masai.Exception.AdminException;
import com.masai.Model.*;
import com.masai.Repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.UserSessionDao;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		if(customer!=null ){
			Customer getCustomer=customerDao.findByEmailId(customer.getEmailId());
			if(getCustomer==null){
				Cart cart=new Cart();
				Cart newCart=cartDao.save(cart);
//				cart.getVegetable().add(e);
//				System.out.println(cart.getVegetable());
//
//				System.out.println(newCart.getVegetable());
				customer.setCart(newCart);
				newCart.setCustomer(customer);
				return customerDao.save(customer);
			}

		}
		throw new CustomerException("customer already present with this emailId");


	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException ,UserException{
		
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Customer not Logged In");
		}

		if(currentUserSession.getRole()==2){

			Customer customer = customerDao.findById(customerId).orElseThrow(()-> new CustomerException("ustomer not exists with Id"+customerId));

			return customer;
		}else{
			throw new CustomerException("Admin not exists with Id"+customerId);
		}


	}

	@Override
	public List<Customer> viewCustomerList(String key) throws CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null)) {
			throw new CustomerException("Invalid session Id or Admin not Logged In");
		}

		if(currentUserSession.getRole()==1){
			List<Customer> customers = customerDao.findAll();

			if(customers.isEmpty()) {
				throw new CustomerException("No Customers Available");
			}

			return customers;
		}
			throw new CustomerException("Invalid Authentication");


	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException, UserException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		Customer updatedCustomer = customerDao.findById(customer.getCustomerId()).get();
		
		
		if(updatedCustomer.equals(null)) {
			throw new CustomerException("No Customer available with "+customer.getEmailId());
		}

		
		customerDao.save(customer);
		
		return customer;
	}

	@Override
	public Customer removeCustomer(Integer customerId, String key) throws CustomerException, UserException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		Optional<Customer> customer = customerDao.findById(customerId);
		if (customer.get() == null) {
			throw new CustomerException("Customer not exists with this email"+customerId);

		}

		
		customerDao.delete(customer.get());
		
		return customer.get();
	}
	
	@Override
	public Customer updatePassword(String email, String phone, String password) throws CustomerException, UserException {
		Customer customer=customerDao.findByEmailIdAndMobileNumber(email,phone);
		if(customer==null){
			throw  new UserException("Please enter correct details");
		}

		customer.setPassword(password);
		return customerDao.save(customer);

	}

}

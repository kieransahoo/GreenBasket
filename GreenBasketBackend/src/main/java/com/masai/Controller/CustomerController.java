package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import com.masai.Model.Customer;
import com.masai.Services.CustomerService;
import com.masai.Services.UserLoginService;



@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerNewCustomerHandler(@RequestBody Customer customer) throws CustomerException{
		
		Customer savedCustomer = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(savedCustomer,HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/{id}/{key}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable("id") Integer customerId,@PathVariable("key") String key) throws CustomerException, UserException{
		
		Customer customer = customerService.viewCustomer(customerId, key);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	
	@GetMapping("/customer/all/{key}")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@PathVariable("key") String key ) throws CustomerException{
		
		List<Customer> customerList = customerService.viewCustomerList(key);
		
		return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
	}
	
	
	@PutMapping("/customer/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer,@PathVariable String key) throws CustomerException, UserException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
		
	}
	
	@PutMapping("/customer/{emailId}/{phone}/{password}")
	public ResponseEntity<Customer> updateCustomerPasswordHandler(@PathVariable("emailId") String emailId, @PathVariable("phone") String phone,@PathVariable("password") String password) throws CustomerException, UserException{

		Customer updatedCustomer = customerService.updatePassword(emailId, phone,password);

		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);

	}
	
	@DeleteMapping("/customer/{id}/{key}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable("id") Integer customerId,@PathVariable("key") String key) throws CustomerException, UserException{
		
		Customer customer = customerService.removeCustomer(customerId, key);

		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}

}

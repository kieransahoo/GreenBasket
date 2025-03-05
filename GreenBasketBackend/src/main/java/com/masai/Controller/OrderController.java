package com.masai.Controller;

import java.util.List;

import com.masai.Exception.AdminException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Exception.OrderException;
import com.masai.Model.Order;
import com.masai.Services.OrderService;

@CrossOrigin
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order/{key}")
	public ResponseEntity<Order> addOrder(@PathVariable String key) throws OrderException, CustomerException, UserException {
		
		Order saveOrder = orderService.addOrder(key);
		
		return new ResponseEntity<Order>(saveOrder,HttpStatus.CREATED);
	}

	@GetMapping("/order/{orderNo}/{key}")
	public ResponseEntity<Order> viewOrder(@PathVariable("orderNo") Integer orderNo,@PathVariable("key") String key) throws OrderException, CustomerException, UserException {
            Order order = orderService.viewOrder(orderNo,key);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}


	@PutMapping("/order/{key}")
	public ResponseEntity<Order> updateOrderDetails(@RequestBody Order order,@PathVariable("key") String key) throws OrderException, CustomerException, UserException {
          Order updatedOrder = orderService.updateOrderDetails(order,key);
		
		return new ResponseEntity<>(updatedOrder,HttpStatus.ACCEPTED);
	}


	@GetMapping("/orders/{key}")
	public ResponseEntity<List<Order>> viewAllOrders(@PathVariable("key") String key) throws OrderException, CustomerException, UserException {
		List<Order> list=orderService.viewAllOrders(key);
		
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/order/{orderNo}/{key}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderNo") Integer orderNo,@PathVariable("key") String key) throws OrderException, CustomerException, UserException {
         Order order = orderService.deleteOrder(orderNo,key);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("/getOrders/{key}")
	public ResponseEntity<List<Order>> viewCompleteOrdersList(@PathVariable("key") String key) throws OrderException, AdminException, UserException {
           List<Order> list=orderService.viewCompleteOrdersList(key);
		
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}


}

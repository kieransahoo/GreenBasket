package com.masai.Services;

import java.util.List;

import com.masai.Exception.AdminException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.OrderException;

import com.masai.Exception.UserException;
import com.masai.Model.Order;

public interface OrderService {
	
	public Order addOrder(String key)throws OrderException, UserException, CustomerException;
	
	public Order viewOrder(Integer orderNo,String key) throws OrderException, UserException, CustomerException;
	
	public Order updateOrderDetails(Order order,String key)throws OrderException,UserException, CustomerException;
	
	public List<Order> viewAllOrders(String key)throws OrderException,UserException, CustomerException;
	
	public List<Order> viewCompleteOrdersList(String key)throws OrderException,UserException, AdminException;
	
	public Order deleteOrder(Integer orderNo,String key)throws OrderException,UserException, CustomerException;
	

}

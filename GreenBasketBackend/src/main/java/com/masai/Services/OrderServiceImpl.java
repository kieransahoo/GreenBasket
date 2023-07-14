package com.masai.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.masai.Exception.AdminException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import com.masai.Model.*;
import com.masai.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.OrderException;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CartDao cartDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Order addOrder(String key) throws OrderException, UserException, CustomerException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));
//		System.out.println("++++++++++++");
//		System.out.println("++++++++++++");
//		System.out.println(customer);
//		System.out.println(customer.getCart());
		Cart cd=customer.getCart();
		//System.out.println(cd);
		List<VegetableDTO> lists=cd.getVegetable();
		//System.out.println(lists);
		if(lists.isEmpty()){
			throw new OrderException("Please add atleast 1 order");
		}
		Order order=new Order();
		Double totalPrice=0.0;


//		List<VegetableDTO> list=customer.getCart().getVegetable();
//		System.out.println(list);
		for(VegetableDTO  c : lists) {
			totalPrice += c.getPrice()*c.getQuantity();
		}
		order.setTotalAmount(totalPrice);
		order.setStatus("Successful");
		order.setCustomer(customer);
		//order.setVegetableList(customer.getCart().getVegetable());
		//System.out.println(customer.getCart());

		for(VegetableDTO v : lists){
			order.getVegetableList().add(v);
		}
//		order.setVegetableList(lists);

		lists.clear();
        cd.setVegetable(lists);
		customer.setCart(cd);

		Order newOrder=orderDao.save(order);
		customer.getOrders().add(newOrder);
		customerDao.save(customer);

		return newOrder;


	}

	@Override
	public Order viewOrder(Integer orderNo,String key) throws OrderException, UserException, CustomerException {


		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));


		Optional<Order> opt=orderDao.findById(orderNo);
		if(opt.isPresent()) {
			Order order = opt.get();
			return order;
		}
		throw new OrderException("Order Id is wrong");
	}

	@Override
	public Order updateOrderDetails(Order order,String key) throws OrderException, UserException, CustomerException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));


		Optional<Order> existingOrder=orderDao.findById(order.getOrderNo());
		if(existingOrder.isPresent()) {
			Order updatedOrder=existingOrder.get();
			updatedOrder=orderDao.save(order);
			return updatedOrder;
		}
		throw new OrderException("Order not found");
	}

	@Override
	public List<Order> viewAllOrders(String key) throws OrderException, UserException, CustomerException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));


		List<Order> list=customer.getOrders();

		if(list.isEmpty()) {
			throw new OrderException("No order found");
		}
		return list;
	}

	@Override
	public Order deleteOrder(Integer orderNo,String key) throws OrderException, UserException, CustomerException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));


		Optional<Order> opt=orderDao.findById(orderNo);
		if(opt.isPresent()) {
			Order order = opt.get();
			orderDao.delete(order);
			return order;
		}
		throw new OrderException("Order Id is wrong");
	}

	@Override
	public List<Order> viewCompleteOrdersList(String key) throws OrderException, UserException, AdminException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=1) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Admin admin = adminDao.findById(currentUserSession.getId()).orElseThrow(()-> new AdminException("Invalid request"));


		List<Order> list=orderDao.findAll();
		if(list.isEmpty()) {
			throw new OrderException("No order found");
		}
		return list;
	}

}

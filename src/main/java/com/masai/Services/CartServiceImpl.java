package com.masai.Services;

import java.util.List;
import java.util.Optional;

import com.masai.Exception.*;
import com.masai.Model.*;
import com.masai.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private VegetableDao vegetable;

	@Autowired
	private UserSessionDao userSessionDao;

	@Autowired
	private CustomerDao customerDao;
	



	@Override
	public VegetableDTO addVegToCart( Integer vegId,String key) throws VegetableException, UserException, CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));



		Cart cart = customer.getCart();


		Optional<Vegetable> stock = vegetable.findById(vegId);
//		vg= vegetable.getVegDto(vegId);
//		vg.setQuantity(1);
		if(stock.isEmpty()) {
				throw new VegetableException("Not found vegetable");
		}
		
		List<VegetableDTO> listVeg=cart.getVegetable();
		for(VegetableDTO vgd:listVeg){
			if(vgd.getVegId()==vegId){
				throw new VegetableException("Product is already added into cart");
			}
		}

			Vegetable vegsto = stock.get();
			if(vegsto.getQuantity() > 0) {
				vegsto.setQuantity(vegsto.getQuantity()-1);
				vegetable.save(vegsto);
			}else {
				throw new VegetableException("Stock is "+vegsto.getQuantity()+" less than given"+1);
			}


		    VegetableDTO vg=new VegetableDTO();
			vg.setVegId(vegsto.getVegId());
			vg.setQuantity(1);
			vg.setName(vegsto.getName());
			vg.setPrice(vegsto.getPrice());
			vg.setImageUrl(vegsto.getImageUrl());


			cart.getVegetable().add(vg);
			cartDao.save(cart);


		return vg;
	}


	@Override
	public Cart increaseVegQantity( Integer vegId,String key) throws VegetableException, UserException, CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));



		  Cart cart = customer.getCart();


			Optional<Vegetable> veg = vegetable.findById(vegId);
			if(veg.get().getQuantity() > 0) {
				veg.get().setQuantity(veg.get().getQuantity()-1);
				vegetable.save(veg.get());
			}else {
				throw new VegetableException("quantity not available");
			}
//			cart.get().getVegetable().forEach(VegetableDTO list: );
			for (VegetableDTO list : cart.getVegetable()) {
				if(list.getVegId() == vegId) {
					list.setQuantity(list.getQuantity()+1);
				}
			}
			Cart newcart = cartDao.save(cart);
			return newcart;

		
	}


	@Override
	public Cart decreaseVegQantity(Integer vegId,String key) throws VegetableException, UserException, CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));



		Cart cart = customer.getCart();


		Optional<Vegetable> veg = vegetable.findById(vegId);
		if(veg.get().getQuantity() > 0) {
			veg.get().setQuantity(veg.get().getQuantity()+1);
			vegetable.save(veg.get());
		}else {
			throw new VegetableException("quantity not available");
		}
//			cart.get().getVegetable().forEach(VegetableDTO list: );
		for (VegetableDTO list : cart.getVegetable()) {
			if(list.getVegId() == vegId) {
				list.setQuantity(list.getQuantity()-1);
			}
		}
		Cart newcart = cartDao.save(cart);
		return newcart;


	}


	@Override
	public Cart removeVegetable(Integer vegId,String key) throws VegetableException, UserException, CustomerException {

		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));



		Cart cart = customer.getCart();

		Integer cartvegcount=0;
			List<VegetableDTO> lists = cart.getVegetable();

			for (VegetableDTO list : lists) {
				if(list.getVegId() == vegId) {
					 lists.remove(list);
					cartvegcount = list.getQuantity();
					Optional<Vegetable> stock = vegetable.findById(vegId);
					if(stock.isEmpty()){
						throw new VegetableException("Something Went Wrong");
					}

					Vegetable stockVeg = stock.get();
					stockVeg.setQuantity(stockVeg.getQuantity()+cartvegcount);
					vegetable.save(stockVeg);//30
					break;
				}
				
			}

			cart.setVegetable(lists);
			Cart newcart = cartDao.save(cart);

//			Optional<Vegetable> stock = vegetable.findById(vegId);
//			if(stock.isEmpty()){
//				throw new VegetableException("Something Went Wrong");
//			}
//
//			Vegetable stockVeg = stock.get();
//			stockVeg.setQuantity(stockVeg.getQuantity()+cartvegcount);
//			vegetable.save(stockVeg);//32

			return newcart;

	}


	@Override
	public Cart removeAllVegetable(String key) throws VegetableException, UserException, CustomerException {


		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));



		Cart cart = customer.getCart();

		cart.getVegetable().clear();

			Cart newcart = cartDao.save(cart);
			return newcart;

	}


	@Override
	public List<VegetableDTO> viewAllItems(String key) throws VegetableException, UserException, CustomerException {


		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if (currentUserSession.equals(null) || currentUserSession.getRole() != 2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(() -> new CustomerException("Customer not exists with Id"));


		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		if (list.isEmpty()) {
			throw new CartException("No items added yet");
		}

//		for(VegetableDTO  c : lists) {
//			totalPrice += c.getPrice()*c.getQuantity();
//		}

		return list;
	}

	@Override
	public Integer viewVegCountInCart(String key) throws VegetableException, UserException, CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if (currentUserSession.equals(null) || currentUserSession.getRole() != 2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(() -> new CustomerException("Customer not exists with Id"));


		Cart cart = customer.getCart();
		List<VegetableDTO> list = cart.getVegetable();
		if (list.isEmpty()) {
			throw new CartException("No items added yet");
		}
		return list.size();

	}

	@Override
	public Double totalPrice(String key) throws CartException, UserException, CustomerException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=2) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Customer customer = customerDao.findById(currentUserSession.getId()).orElseThrow(()-> new CustomerException("Customer not exists with Id"));

		Cart cd=customer.getCart();

		List<VegetableDTO> lists=cd.getVegetable();

		if(lists.isEmpty()){
			throw new OrderException("Please add atleast 1 order");
		}

		Double totalPrice=0.0;


		for(VegetableDTO  c : lists) {
			totalPrice += c.getPrice()*c.getQuantity();
		}

		return totalPrice;
	}


}

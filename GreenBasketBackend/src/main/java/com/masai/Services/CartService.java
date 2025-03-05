package com.masai.Services;

import java.util.List;

import com.masai.Exception.CartException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import com.masai.Exception.VegetableException;
import com.masai.Model.Cart;
import com.masai.Model.Vegetable;
import com.masai.Model.VegetableDTO;

public interface CartService {
	
//	public Cart createCart(Cart cart)throws CartException;
//	public Cart addToCart(Integer vegId,String key) throws VegetableException,UserException, CustomerException;
	
	public VegetableDTO addVegToCart(Integer vegId,String key)throws VegetableException, UserException, CustomerException;
	public Cart increaseVegQantity(Integer vegId,String key) throws VegetableException,UserException, CustomerException;
	public Cart decreaseVegQantity(Integer vegId,String key) throws VegetableException, UserException, CustomerException;
	public Cart removeVegetable(Integer vegId,String key) throws VegetableException,UserException, CustomerException;
	public Cart removeAllVegetable(String key) throws VegetableException,UserException, CustomerException;

	public List<VegetableDTO> viewAllItems(String key) throws VegetableException,UserException, CustomerException;

	public Integer viewVegCountInCart(String key) throws VegetableException,UserException, CustomerException;

	public Double totalPrice(String key)throws CartException,UserException,CustomerException;

}

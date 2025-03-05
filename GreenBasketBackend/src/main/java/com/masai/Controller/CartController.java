package com.masai.Controller;

import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Model.Cart;
import com.masai.Model.VegetableDTO;
import com.masai.Services.CartService;
@CrossOrigin
@RestController
public class CartController {

	//service Impl
	@Autowired
	private CartService cartservice;
	
//	@PostMapping("/cart")
//	public ResponseEntity<Cart> createCartHandller(@RequestBody Cart cart){
//		return new ResponseEntity<>(cartservice.createCart(cart),HttpStatus.CREATED);
//	}
//
	@PostMapping("/add/{vegId}/{key}")
	public ResponseEntity<VegetableDTO> addToCartHandller(@PathVariable Integer vegId,@PathVariable String key) throws CustomerException, UserException {
		VegetableDTO vegs = cartservice.addVegToCart(vegId, key);
		return new ResponseEntity<VegetableDTO>(vegs, HttpStatus.CREATED);
	}
	
	@PutMapping("/ince/{vegId}/{key}")
	public ResponseEntity<Cart> increaseVegQuantityHandller(@PathVariable Integer vegId,@PathVariable String key) throws CustomerException, UserException {
		Cart cart = cartservice.increaseVegQantity(vegId, key);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	@PutMapping("/decr/{vegId}/{key}")
	public ResponseEntity<Cart> decreaseVegQuantityHandller(@PathVariable Integer vegId,@PathVariable String key) throws CustomerException, UserException {
		Cart cart = cartservice.decreaseVegQantity(vegId, key);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	@DeleteMapping("/remove/{vegId}/{key}")
	public ResponseEntity<Cart> removeVegetableHandller(@PathVariable Integer vegId,@PathVariable String key) throws CustomerException, UserException {
		Cart cart = cartservice.removeVegetable(vegId, key);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	@GetMapping("removeAll/{key}")
	public ResponseEntity<Cart> removeAllVegetableHandller(@PathVariable String key) throws CustomerException, UserException {
		Cart cart = cartservice.removeAllVegetable(key);
		return new ResponseEntity<>(cart,HttpStatus.FOUND);
	}
	@GetMapping("view/{key}")
	public ResponseEntity<List<VegetableDTO>> listOfAllVegetableHandller(@PathVariable String key) throws CustomerException, UserException {

		List<VegetableDTO> lists = cartservice.viewAllItems(key);
		return new ResponseEntity<>(lists,HttpStatus.ACCEPTED);
	}

	@GetMapping("count/{key}")
	public ResponseEntity<Integer> getCartCountHandler(@PathVariable String key) throws CustomerException, UserException {

		Integer count = cartservice.viewVegCountInCart(key);
		return new ResponseEntity<>(count,HttpStatus.ACCEPTED);
	}

	@GetMapping("getTotalAmount/{key}")
	public ResponseEntity<Double> totalPriceHandller(@PathVariable String key) throws CustomerException, UserException {

		Double amount = cartservice.totalPrice(key);
		return new ResponseEntity<>(amount,HttpStatus.ACCEPTED);
	}
	
}

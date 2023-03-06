package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

	
}

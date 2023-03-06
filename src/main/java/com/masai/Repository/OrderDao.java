package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.Model.Order;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>{

////	@Query("SELECT c.order FROM Customer c WHERE c.customerId=?1")
//	public List<Order> findByCustomerId(Integer customerId);
}

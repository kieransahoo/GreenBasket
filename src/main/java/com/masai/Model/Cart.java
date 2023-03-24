package com.masai.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> order=new ArrayList<>();

	@ElementCollection
	@Embedded
	private List<VegetableDTO> vegetable=new ArrayList<>();

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer cartId, Customer customer, List<Order> order, List<VegetableDTO> vegetable) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.order = order;
		this.vegetable = vegetable;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<VegetableDTO> getVegetable() {
		return vegetable;
	}

	public void setVegetable(List<VegetableDTO> vegetable) {
		this.vegetable = vegetable;
	}

	
	

}

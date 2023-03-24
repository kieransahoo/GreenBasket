package com.masai.Model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String customerName;
	
	private String mobileNumber;
	
	private String emailId;
	
	private String password;

	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
//
//	@ManyToMany(mappedBy = "customers",cascade = CascadeType.ALL)
//	private List<Feedback> feedbacks;
//
//
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders=new ArrayList<>();

	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<BillDetails> bills=new ArrayList<>();

	@ElementCollection
	@Embedded
	private List<Address> address=new ArrayList<>();
	
//	@Embedded
//	@ElementCollection(mapp)
//	private Address address;

	final private Integer role=2;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer customerId, String customerName, String mobileNumber, String emailId, String password,
			Cart cart, List<Order> orders, List<BillDetails> bills, List<Address> address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.password = password;
		this.cart = cart;
		this.orders = orders;
		this.bills = bills;
		this.address = address;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<BillDetails> getBills() {
		return bills;
	}

	public void setBills(List<BillDetails> bills) {
		this.bills = bills;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Integer getRole() {
		return role;
	}


	
	


}

package com.masai.Model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderNo;
	
	private Double totalAmount;
	
	private String status;

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//	@JoinColumn(name = "orderNo")
//	private Cart cart;


	@ManyToOne
	private Customer customer;
	
//  @Embedded
//	@ElementCollection
//	private List<VegetableDTO> vegetableList=new ArrayList<>();

	@ElementCollection
	@Embedded
	private List<VegetableDTO> vegetableList=new ArrayList<>();

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer orderNo, Double totalAmount, String status, Customer customer,
			List<VegetableDTO> vegetableList) {
		super();
		this.orderNo = orderNo;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customer = customer;
		this.vegetableList = vegetableList;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<VegetableDTO> getVegetableList() {
		return vegetableList;
	}

	public void setVegetableList(List<VegetableDTO> vegetableList) {
		this.vegetableList = vegetableList;
	}
	

//	private Integer customerId;

	
	
}

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



	
	

}

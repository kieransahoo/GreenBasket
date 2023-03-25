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

//	private Integer customerId;

	
	
}

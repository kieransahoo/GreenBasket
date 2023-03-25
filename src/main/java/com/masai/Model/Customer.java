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




	
	


}

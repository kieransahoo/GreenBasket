package com.masai.Model;

import com.masai.Repository.CustomerDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	private String flatNo;
	private String buildingName;
	private String area;
	private String city;
	private String state;
	private String pincode;


	
	//private Customer customer;



	
	
	
}

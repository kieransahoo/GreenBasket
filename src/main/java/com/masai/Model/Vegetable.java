
package com.masai.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vegetable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vegId;

	private String name;

	private String type;


	private Integer quantity;

	private Double price;

	private String imageUrl;

	public Vegetable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vegetable(Integer vegId, String name, String type, Integer quantity, Double price, String imageUrl) {
		super();
		this.vegId = vegId;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Integer getVegId() {
		return vegId;
	}

	public void setVegId(Integer vegId) {
		this.vegId = vegId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	
	
	
}

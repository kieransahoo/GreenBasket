
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




	
	
	
}

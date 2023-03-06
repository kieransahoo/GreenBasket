package com.masai.Repository;

import java.util.List;

import com.masai.Model.Customer;
import com.masai.Model.VegetableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.Model.Vegetable;


@Repository
public interface VegetableDao extends JpaRepository<Vegetable, Integer> {


	public List<Vegetable> findByName(String Name);

	@Query("SELECT new com.masai.Model.VegetableDTO(v.vegId,v.name,v.price,v.imageUrl) FROM Vegetable v WHERE v.vegId= :vegId")
	public VegetableDTO getVegDto(@Param("vegId") Integer vegId);



}

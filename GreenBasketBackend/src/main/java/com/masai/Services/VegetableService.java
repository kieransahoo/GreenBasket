package com.masai.Services;

import java.util.List;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Exception.VegetableException;
import com.masai.Model.Vegetable;



public interface VegetableService {

	public Vegetable addVegetable(Vegetable veg,String key) throws VegetableException, UserException, AdminException;
	
	public Vegetable updateVegetable(Vegetable veg,String key) throws VegetableException, UserException, AdminException;
	
	public Vegetable deleteVegetable (Integer vid,String key) throws VegetableException, UserException, AdminException;
	
	public List<Vegetable> listOfVegetables() throws VegetableException;
	
	public List<Vegetable> getVegetableByName(String veg_name) throws VegetableException;
	
	
	
}

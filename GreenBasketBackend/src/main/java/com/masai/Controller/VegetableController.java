package com.masai.Controller;

import java.util.List;


import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Exception.VegetableException;
import com.masai.Model.Vegetable;
import com.masai.Services.VegetableService;


@CrossOrigin
@RestController
public class VegetableController {

	
	@Autowired
	private VegetableService vs;
	
	
	@PostMapping("/vegetable/{key}")
	public ResponseEntity<Vegetable> registerVegetable(@RequestBody Vegetable vegetable,@PathVariable("key") String key) throws AdminException, UserException {

		Vegetable veg=vs.addVegetable(vegetable,key);
		return new ResponseEntity<>(veg,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/vegetables")
	public ResponseEntity<List<Vegetable>> allVegetableList() throws VegetableException{
		
		List<Vegetable> list =vs.listOfVegetables();
		return new ResponseEntity<List<Vegetable>>(list,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/vegetable/{vid}/{key}")
	public ResponseEntity<Vegetable> deleteVeg(@PathVariable("vid") Integer vId,@PathVariable("key") String key) throws VegetableException, AdminException, UserException {
		
		Vegetable veg =vs.deleteVegetable(vId,key);
		return new ResponseEntity<Vegetable>(veg,HttpStatus.OK);
		
	}
	
	@PutMapping("/vegetable/{key}")
	public ResponseEntity<Vegetable> update(@RequestBody Vegetable vegetable,@PathVariable("key") String key) throws VegetableException, AdminException, UserException {
		
		Vegetable updated =vs.updateVegetable(vegetable,key);
		return new ResponseEntity<Vegetable>(updated,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/vegetable/vegname/{vegname}")
	public ResponseEntity<List<Vegetable>> byVegName(@PathVariable("vegname") String veg_name) throws VegetableException{
		
		List<Vegetable> list =vs.getVegetableByName(veg_name);
		
		return new ResponseEntity<List<Vegetable>>(list,HttpStatus.OK);
		
	}

	
	
}

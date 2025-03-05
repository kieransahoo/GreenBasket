package com.masai.Services;

import java.util.List;
import java.util.Optional;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.CurrentUserSession;
import com.masai.Repository.AdminDao;
import com.masai.Repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.VegetableException;
import com.masai.Model.Vegetable;
import com.masai.Repository.VegetableDao;




@Service
public class VegetableServiceImpl implements VegetableService {
	
	
	@Autowired
	private VegetableDao vr;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public Vegetable addVegetable(Vegetable veg,String key) throws VegetableException, UserException, AdminException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=1) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Admin admin = adminDao.findById(currentUserSession.getId()).orElseThrow(()-> new AdminException("Invalid request"));



		if(veg!=null) {
			Vegetable vegetable=vr.save(veg);

			return vegetable;
		}
		else {
			throw new VegetableException("Input might be incorrect");
		}
		
	}

	@Override
	public Vegetable updateVegetable(Vegetable veg,String key) throws VegetableException, UserException, AdminException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=1) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Admin admin = adminDao.findById(currentUserSession.getId()).orElseThrow(()-> new AdminException("Invalid request"));


		// TODO Auto-generated method stub
		Optional<Vegetable> vegetable=vr.findById(veg.getVegId());
		Vegetable update=null;
		if(vegetable.isPresent()) {
			update=vegetable.get();
			update.setPrice(veg.getPrice());
			update.setQuantity(veg.getQuantity());
			update.setImageUrl(veg.getImageUrl());
			update.setName(veg.getName());
			Vegetable vg=vr.save(update);
			//System.out.println("Price And Unit Update");
			return vg;
		}else{
			throw new VegetableException("Not Valid Type");
		}
		

	}

	@Override
	public Vegetable deleteVegetable(Integer vid,String key) throws VegetableException, UserException, AdminException {
		CurrentUserSession currentUserSession = userSessionDao.findByUuid(key);

		if(currentUserSession.equals(null) || currentUserSession.getRole()!=1) {
			throw new UserException("Invalid session Id or not Logged In");
		}

		Admin admin = adminDao.findById(currentUserSession.getId()).orElseThrow(()-> new AdminException("Invalid request"));


		Optional<Vegetable> vegetable=vr.findById(vid);
		if(vegetable.isPresent()) {
			Vegetable veg=vegetable.get();
			vr.delete(veg);
			//System.out.println("Vegetable is Deleted!");
			return veg;
		}
		else
		{
			throw new VegetableException("not valid Vegetable id:"+vid);
		}
	}

	@Override
	public List<Vegetable> listOfVegetables() throws VegetableException{


		List<Vegetable> listOfVegetables=vr.findAll();
		if(listOfVegetables.isEmpty()) {
			throw new VegetableException("There is no Vegetable.");
		}
		else {
			return listOfVegetables;
		}
	}

	@Override
	public List<Vegetable> getVegetableByName(String veg_name) throws VegetableException {
		// TODO Auto-generated method stub
		
		List<Vegetable> list=vr.findByName(veg_name);
		if(list!=null) {
			return list;
		}
		else {
			throw new VegetableException("Nat valid vegetable name");
		}
	}


}

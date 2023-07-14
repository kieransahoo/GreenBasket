package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Services.AdminService;
import com.masai.Services.UserLoginService;


@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> registerNewAdminHandler(@RequestBody Admin admin) throws AdminException{
		
		Admin savedAdmin = adminService.addAdmin(admin);
		
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{id}/{key}")
	public ResponseEntity<Admin> viewAdminHandler(@PathVariable("id") Integer admin_id,@PathVariable("key") String key) throws AdminException, UserException{
		
		Admin admin = adminService.viewAdmin(admin_id, key);
		
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/admin/{key}")
	public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin,@PathVariable String key) throws AdminException, UserException{
		
		Admin updatedAdmin = adminService.updateAdmin(admin, key);
		
		return new ResponseEntity<Admin>(updatedAdmin,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/{id}/{key}")
	public ResponseEntity<Admin> removeAdminHandler(@PathVariable("id") Integer admin_id,@PathVariable("key") String key) throws AdminException, UserException{
		
		Admin admin = adminService.removeAdmin(admin_id, key);
		
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
		
	}

}

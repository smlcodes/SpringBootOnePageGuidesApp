package com.springdemo.controller;

import com.springdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springdemo.service.UserService;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService service ;

	

	@GetMapping("/")
	public String viewHomePage() {
		return "User Details API";
	}
	


	// display list of users
	@GetMapping("/all")
	public List<User> listUserss(Model model) {
		System.out.println(" ===> listUserss");
		return service.getAllUsers();
	}


	@GetMapping("/addall")
	public String addDummyUsers() {
		System.out.println(" ===> listUserss");

		for(int i=0; i<10; i++){
			User user = new User();
			user.setName(nameGen());
			user.setSalary(salaryGen());
			System.out.println("added : "+user);
			service.saveUser(user);
		}
		return "User added";

	}



	@PostMapping("/saveUser")
	public User saveUsers(@RequestBody User user) {
		System.out.println("saveuser : "+user);
		return service.saveUser(user);

	}


	@GetMapping("/deleteUser/{id}")
	public String deleteUsers(@PathVariable(value = "id") long id) {
		// call delete user method
		this.service.deleteUsersById(id);
		return "deleted";
	}


	@GetMapping("/querysln")
	public String querysln() {
		System.out.println(" ===> querysln");
		return service.querySln();
	}


	@GetMapping("/completableFeature")
	public List<User> completableFeature() {
		System.out.println(" ===> completableFeature");
		System.out.println("sample data : https://www.mockaroo.com/");
		return service.completableFeature();
	}


	private String nameGen()
	{
		String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZSATYA";
		StringBuilder s = new StringBuilder(6);
		int i;
		for ( i=0; i<8; i++) {
			int ch = (int)(AlphaNumericStr.length() * Math.random());
			s.append(AlphaNumericStr.charAt(ch));
		}
		return s.toString();
	}

	private Long salaryGen()
	{
		Random r = new Random();
		int low = 5000;
		int high = 90000;
		long result = r.nextInt(high-low) + low;
		return result;
	}



}

package com.springdemo.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.springdemo.model.Users;
import com.springdemo.service.UsersService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
    private UsersService service ;

	

	@GetMapping("/")
	public String viewHomePage() {
		return "User Details API";
	}
	


	// display list of users
	@GetMapping("/all")
	public List<Users> listUserss(Model model) {
		System.out.println(" ===> listUserss");
		return service.getAllUserss();
	}


	@GetMapping("/addall")
	public String addDummyUsers() {
		System.out.println(" ===> listUserss");

		for(int i=0; i<10; i++){
			Users user = new Users();
			user.setName(nameGen());
			System.out.println("added : "+user);
			service.saveUser(user);
		}
		return "Users added";

	}



	@PostMapping("/saveUser")
	public String saveUsers(@ModelAttribute("user") Users user) {
		// save user to database
		System.out.println(" ===================== ");
		System.out.println("saveUsers : "+user);
		System.out.println(" ===================== ");
		service.saveUser(user);
		return "saved";
	}


	@GetMapping("/deleteUser/{id}")
	public String deleteUsers(@PathVariable(value = "id") long id) {
		// call delete user method
		this.service.deleteUsersById(id);
		return "deleted";
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
}

package com.springdemo.service;

import java.util.List;

import com.springdemo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	List<User> getAllUsers();

	User saveUser(User User);

	User getUsersById(long id);

	void deleteUsersById(long id);

	String querySln();

	List<User> completableFeature();
}
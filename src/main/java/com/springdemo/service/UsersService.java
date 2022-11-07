package com.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springdemo.model.Users;

@Service
public interface UsersService {
	List<Users> getAllUserss();

	void saveUser(Users Users);

	Users getUsersById(long id);

	void deleteUsersById(long id);
}
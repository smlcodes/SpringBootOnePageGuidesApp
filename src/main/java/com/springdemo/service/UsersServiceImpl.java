package com.springdemo.service;

import java.util.List;

import com.springdemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.model.Users;


@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
    UsersRepository repository;

	@Override
	public List<Users> getAllUserss() {
		// TODO Auto-generated method stub
		System.out.println("===> Repositoy :getAllUserss "+repository.findAll());
		return repository.findAll();
	}

	@Override
	public void saveUser(Users user) {
		// TODO Auto-generated method stub
		System.out.println("Saving Start : "+user);
		Users e = repository.save(user);
		System.out.println(" Users Data Saved : " + e);

	}

	@Override
	public Users getUsersById(long id) {
		Users e = repository.getById(id);
		System.out.println("Users getUsersById : " + e);
		return e;
	}

	@Override
	public void deleteUsersById(long id) {
		Users e = repository.getById(id);
		repository.delete(e);
		System.out.println("Users Deleted : ");

	}
}

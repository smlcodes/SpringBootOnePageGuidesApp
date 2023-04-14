package com.springdemo.service;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.springdemo.model.QUser;
import com.springdemo.model.User;
import com.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UseServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		System.out.println("===> Repositoy :getAllUsers "+repository.findAll());
		return repository.findAll();
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("Saving Start : "+user);
		User e = repository.save(user);
		System.out.println(" User Data Saved : " + e);

	}

	@Override
	public User getUsersById(long id) {
		User e = repository.getById(id);
		System.out.println("User getUserById : " + e);
		return e;
	}

	@Override
	public void deleteUsersById(long id) {
		User e = repository.getById(id);
		repository.delete(e);
		System.out.println("User Deleted : ");
	}

	@Override
	public String querySln() {
		QUser users = QUser.user;

		String result="";


		System.out.println(" -- users having salary greater than 10000 order by salary --");
		result = result+"-- users having salary greater than 10000 order by salary --";
		//BooleanExpression implements Predicate
		//goe='greater than or equals'
		BooleanExpression booleanExpression = QUser.user.salary.goe(10000);
		OrderSpecifier<Long> orderSpecifier = QUser.user.salary.asc();
		Iterable<User> users1 = repository.findAll(booleanExpression, orderSpecifier);
		for (User user : users1) {
			result= result+"\n "+user;
		}



		System.out.println(" -- users salary between 3000 and 5000 --");
		result = result+"\n \n \n -- users salary between 3000 and 5000 --";
		BooleanExpression booleanExpression2 = QUser.user.salary.between(30000, 50000);
		Iterable<User> users3 = repository.findAll(booleanExpression2);
		for (User user : users3) {
			result= result+"\n "+user;
		}

		System.out.println(" -- find users YNDFBTUG --");
		result = result+"\n \n \n-- find users YNDFBTUG --";
		BooleanExpression booleanExpression3 = QUser.user.name.eq("YNDFBTUG");
		Optional<User> opt = repository.findOne(booleanExpression3);
		result= result+"\n "+opt.get();


		return result;
	}

	@Override
	public List<User> completableFeature() {
		return null;
	}


}

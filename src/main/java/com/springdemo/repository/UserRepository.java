package com.springdemo.repository;

import com.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Long> , QuerydslPredicateExecutor<User> {
     
}
package com.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springdemo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>  {
     
}
package com.springdemo.service;

import com.springdemo.model.Employee;
import com.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

    @Autowired
    EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Employee> allEmployees() {
        return repository.findAll();
    }
}

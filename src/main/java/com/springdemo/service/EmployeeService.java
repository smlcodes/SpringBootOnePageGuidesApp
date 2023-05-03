package com.springdemo.service;

import com.springdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService
{
    public Employee save(Employee employee);

    public Employee getById(Long id);

    public List<Employee> allEmployees();

}

package com.springdemo.controller;

import com.springdemo.model.Employee;
import com.springdemo.repository.EmployeeRepository;
import com.springdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Employee> allEmployees() {
        return service.allEmployees();
    }

}

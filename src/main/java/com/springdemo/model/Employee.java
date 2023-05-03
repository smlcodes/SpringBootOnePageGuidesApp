package com.springdemo.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double salary;



}

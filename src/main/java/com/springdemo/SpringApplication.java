package com.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        System.out.println(" =================================== ");
        System.out.println( " Spring ReportsUtilQueryCheck Application ...");
        System.out.println( "http://localhost:8181/springdemo/api/all");
        System.out.println( "http://localhost:8181/springdemo/api/addall");
        System.out.println(" http://localhost:8181/springdemo/swagger-ui/index.html");
        System.out.println( "http://localhost:8181/actuator/refresh");
        System.out.println("http://localhost:8181/springdemo/api/querysln");


        System.out.println("If any errors in QueryDSL, RightClick > Maven > Generate Sources & Reload project");
        
        
        System.out.println(" =================================== ");
    }

}
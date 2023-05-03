package com.springdemo;

import com.springdemo.utils.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        System.out.println(" *********************** ");
        System.out.println("http://localhost:8181/springdemo/swagger-ui/index.html");
        System.out.println(" *********************** \n \n ");

        System.out.println(" \n \t UserController \n ************************ ");
        System.out.println( "http://localhost:8181/springdemo/user/list");
        System.out.println( "http://localhost:8181/springdemo/user/addall");
        System.out.println("http://localhost:8181/springdemo/user/querysln");
        System.out.println( "http://localhost:8181/actuator/refresh");


        System.out.println("  \n \t EmployeeController \n ************************ ");
        System.out.println(" http://localhost:8181/springdemo/employee");
        System.out.println(" http://localhost:8181/springdemo/employee/101");
        System.out.println(" http://localhost:8181/springdemo/employee/all");

        System.out.println("If any errors in QueryDSL, RightClick > Maven > Generate Sources & Reload project");
        System.out.println("Basic Auth Token :"+ Base64Util.encode("user", "password"));
        
        System.out.println("\n \n \n =================================== ");
    }

}
package com.springdemo.controller;

import com.springdemo.model.Employee;
import com.springdemo.repository.EmployeeRepository;
import com.springdemo.repository.UserRepository;
import com.springdemo.utils.JasperReportsUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class JasperReportsController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JasperReportsUtil reportsUtil;

    @GetMapping("/employeesReport/{reportType}")
    public String employeesReport(@PathVariable String reportType) throws Exception {

        List<Employee> employees = employeeRepository.findAll();

        //1.Load & Read .jrxml file
        File file = ResourceUtils.getFile("classpath:reports/empoyeeReport.jrxml");

        //2.Compile JasperReport (.jrxml)
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        //3.Get Datasource & provide Datasource to jasperReport. Here we are using Java Bean class, so datasource will be BeanDatasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        //4.fill the Report with Datasource data.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created By", "Satya");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);

        String desFilename ="";
        //5.Generate & Export Report based on reportType
        if(reportType.equalsIgnoreCase("html")){
            desFilename = "generatedReports/"+"Employee_"+ LocalDate.now()+".html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint,desFilename );

        } else if (reportType.equalsIgnoreCase("pdf")) {
            desFilename = "generatedReports/"+"Employee_"+ LocalDate.now()+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint,desFilename );
        }

        return "Report Generated, at : "+desFilename;
    }


}

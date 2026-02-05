package com.practicespringboot.employeemanagement.controller;

import com.practicespringboot.employeemanagement.dto.OnBoardingRequest;
import com.practicespringboot.employeemanagement.entity.Employee;
import com.practicespringboot.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> index() {

        return employeeService.getAllEmployee();
    }

    @PostMapping("/employee/onboarding")
    public Employee onboard(@Valid @RequestBody OnBoardingRequest req) {

        return employeeService.OnboardEmployee(req);
    }
}

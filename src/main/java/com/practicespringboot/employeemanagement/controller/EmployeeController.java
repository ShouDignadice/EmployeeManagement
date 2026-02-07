package com.practicespringboot.employeemanagement.controller;

import com.practicespringboot.employeemanagement.dto.EmployeeUpdateRequest;
import com.practicespringboot.employeemanagement.dto.OnBoardingRequest;
import com.practicespringboot.employeemanagement.entity.Employee;
import com.practicespringboot.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> index() {

        return employeeService.getAllEmployee();
    }

    @PostMapping("/employees/onboarding")
    public Employee onboard(@Valid @RequestBody OnBoardingRequest req) {

        return employeeService.onboardEmployee(req);
    }

    @PutMapping("/employees/{id}")
    public Employee update(@PathVariable long id,
                           @Valid @RequestBody EmployeeUpdateRequest req) {

        return employeeService.updateEmployee(id, req);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee has been deleted");
    }
}

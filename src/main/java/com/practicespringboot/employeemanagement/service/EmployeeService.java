package com.practicespringboot.employeemanagement.service;

import com.practicespringboot.employeemanagement.dto.OnBoardingRequest;
import com.practicespringboot.employeemanagement.entity.Employee;
import com.practicespringboot.employeemanagement.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee() {

        return employeeRepository.findAll();
    }

    public Employee OnboardEmployee(OnBoardingRequest req) {

        Employee employee = new Employee();

        String email = req.getEmail().trim().toLowerCase();

        if (employeeRepository.existsByEmail(email)) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        employee.setEmail(email);
        employee.setFirstName(req.getFirstName().trim());
        employee.setLastName(req.getLastName().trim());
        employee.setJobTitle(req.getJobTitle().trim());

        return employeeRepository.save(employee);
    }
}

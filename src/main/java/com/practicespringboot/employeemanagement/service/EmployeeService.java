package com.practicespringboot.employeemanagement.service;

import com.practicespringboot.employeemanagement.dto.EmployeeUpdateRequest;
import com.practicespringboot.employeemanagement.dto.OnBoardingRequest;
import com.practicespringboot.employeemanagement.entity.Employee;
import com.practicespringboot.employeemanagement.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Employee onboardEmployee(OnBoardingRequest req) {

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

    public Employee updateEmployee(Long id, EmployeeUpdateRequest req) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        String newEmail = req.getEmail().trim().toLowerCase();
        String currentEmail = employee.getEmail() == null ? null : employee.getEmail().trim().toLowerCase();

        if(currentEmail == null || !currentEmail.equals(newEmail)) {
            if(employeeRepository.existsByEmail(newEmail)) {

                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
            }

            employee.setEmail(newEmail);
        }

        employee.setFirstName(req.getFirstName().trim());
        employee.setLastName(req.getLastName().trim());
        employee.setJobTitle(req.getJobTitle().trim());

        try {

            return employeeRepository.save(employee);

        } catch (DataIntegrityViolationException e) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
    }
}

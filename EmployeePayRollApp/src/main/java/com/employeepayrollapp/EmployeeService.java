package com.employeepayrollapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final List<UserDTO> employees = new ArrayList<>();

    public List<UserDTO> getEmployees() {
        // Return the list of employees
        return employees;
    }

    public UserDTO getEmployeeById(Long id) {
        // Find the employee by ID or throw an exception if not found
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    public UserDTO createEmployee(UserDTO employee) {
        // Add the new employee to the list
        employees.add(employee);
        return employee;
    }

    public UserDTO updateEmployee(Long id, UserDTO updatedEmployee) {
        // Update the employee details
        Optional<UserDTO> existingEmployee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (!existingEmployee.isPresent()) {
            throw new RuntimeException("Employee not found!");
        }

        UserDTO employee = existingEmployee.get();
        employee.setName(updatedEmployee.getName());
        employee.setSalary(updatedEmployee.getSalary());
        return employee;
    }

    public void deleteEmployee(Long id) {
        // Remove the employee from the list
        boolean removed = employees.removeIf(employee -> employee.getId().equals(id));

        if (!removed) {
            throw new RuntimeException("Employee not found!");
        }
    }
}

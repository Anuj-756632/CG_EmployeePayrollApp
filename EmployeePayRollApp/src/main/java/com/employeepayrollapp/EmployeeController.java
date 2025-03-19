package com.employeepayrollapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<UserDTO> employees = new ArrayList<>();

    @GetMapping
    public List<UserDTO> getEmployees() {
        // Return the list of employees
        return employees;
    }

    @GetMapping("/{id}")
    public UserDTO getEmployeeById(@PathVariable Long id) {
        // Find the employee by ID
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    @PostMapping
    public UserDTO createEmployee(@RequestBody UserDTO employee) {
        // Add the new employee to the list
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public UserDTO updateEmployee(@PathVariable Long id, @RequestBody UserDTO updatedEmployee) {
        // Update the employee details
        UserDTO employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
        employee.setName(updatedEmployee.getName());
        employee.setSalary(updatedEmployee.getSalary());
        return employee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        // Remove the employee from the list
        employees.removeIf(employee -> employee.getId().equals(id));
    }
}

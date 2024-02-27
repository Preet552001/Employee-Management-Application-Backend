package com.example.employee.controller;

import com.example.employee.dto.EmployeeDTO;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<?> getEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{employeeId}")
    private ResponseEntity<?> getEmployeeByID(@PathVariable("employeeId") int employeeId){
        return employeeService.employeeById(employeeId);
    }

    @PostMapping
    private ResponseEntity<?> postEmployee(Employee employee){
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping
    private ResponseEntity<?> putEmployee(Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    private ResponseEntity<?> putEmployee(@PathVariable("employeeId") int employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
}

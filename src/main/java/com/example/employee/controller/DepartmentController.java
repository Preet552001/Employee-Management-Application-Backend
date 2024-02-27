package com.example.employee.controller;

import com.example.employee.model.Department;
import com.example.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    private ResponseEntity<?> getDepartment(){
        return departmentService.allDepartment();
    }

    @PostMapping
    private ResponseEntity<?> postDepartment(Department department){
        return departmentService.addNewDepartment(department);
    }

    @PutMapping
    private ResponseEntity<?> putDepartment(Department department){
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping(path = "{departmentId}")
    private ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") int departmentId){
        return departmentService.deleteDepartment(departmentId);
    }
}

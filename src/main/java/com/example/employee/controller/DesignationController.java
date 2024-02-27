package com.example.employee.controller;

import com.example.employee.model.Designation;
import com.example.employee.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    DesignationService designationService;

    @GetMapping
    private ResponseEntity<?> getDepartment(){
        return designationService.allDesignation();
    }

    @PostMapping
    private ResponseEntity<?> postDepartment(Designation designation){
        return designationService.addNewDesignation(designation);
    }

    @PutMapping
    private ResponseEntity<?> putDepartment(Designation designation){
        return designationService.updateDesignation(designation);
    }

    @DeleteMapping(path = "{designationId}")
    private ResponseEntity<?> deleteDepartment(@PathVariable("designationId") int designationId){
        return designationService.deleteDesignation(designationId);
    }
}

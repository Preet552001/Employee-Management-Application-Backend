package com.example.employee.controller;

import com.example.employee.model.ExpenseType;
import com.example.employee.model.LeaveType;
import com.example.employee.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaveType")
public class LeaveTypeController {

    @Autowired
    LeaveTypeService leaveTypeService;

    @GetMapping
    private ResponseEntity<?> getLeaveType(){
        return leaveTypeService.allLeaveType();
    }

    @PostMapping
    private ResponseEntity<?> postLeaveType(LeaveType leaveType){
        return leaveTypeService.addNewLeaveType(leaveType);
    }

    @PutMapping
    private ResponseEntity<?> putLeaveType(LeaveType leaveType){
        return leaveTypeService.updateLeaveType(leaveType);
    }

    @DeleteMapping(path = "{leaveTypeId}")
    private ResponseEntity<?> deleteLeaveType(@PathVariable("leaveTypeId") int leaveTypeId){
        return leaveTypeService.deleteLeaveType(leaveTypeId);
    }
}

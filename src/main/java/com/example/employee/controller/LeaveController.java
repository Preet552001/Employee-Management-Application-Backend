package com.example.employee.controller;

import com.example.employee.model.Leave;
import com.example.employee.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @GetMapping
    public ResponseEntity<?> getLeave(){
        return leaveService.allLeave();
    }

    @GetMapping(path = "employee/{employeeId}")
    public ResponseEntity<?> getLeaveByEmployeeId(@PathVariable("employeeId") int employeeId){
        return leaveService.allLeaveByEmployeeId(employeeId);
    }

    @PostMapping
    public ResponseEntity<?> postLeave(Leave leave){
        return leaveService.addLeave(leave);
    }

    @PutMapping
    public ResponseEntity<?> putLeave(Leave leave){
        return leaveService.updateLeave(leave);
    }

    @DeleteMapping(path = "{leaveId}")
    public ResponseEntity<?> deleteLeave(@PathVariable("leaveId") int leaveId){
        return leaveService.deleteLeave(leaveId);
    }
}

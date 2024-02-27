package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.Leave;
import com.example.employee.model.LeaveType;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.LeaveRepository;
import com.example.employee.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {
    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Leave>> allLeave(){
        List<Leave> leaveList=leaveRepository.findAll();
        if(leaveList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(leaveList));
    }

    public ResponseEntity<List<Leave>> allLeaveByEmployeeId(int employeeId){
        List<Leave> leaveList=leaveRepository.findAllByEmployeeId(employeeId);
        if(leaveList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(leaveList));
    }

    public ResponseEntity<Void> addLeave(Leave leave){
        if( (employeeRepository.findById(leave.getEmployeeId()).orElse(new Employee())).getId()== 0  || (leaveTypeRepository.findById(leave.getLeaveTypeId()).orElse(new LeaveType())).getId() == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Leave updateLeave=leaveRepository.save(leave);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> updateLeave(Leave leave){
        if( (employeeRepository.findById(leave.getEmployeeId()).orElse(new Employee())).getId()== 0  || (leaveTypeRepository.findById(leave.getLeaveTypeId()).orElse(new LeaveType())).getId() == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Leave newLeave=leaveRepository.save(leave);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> deleteLeave(int leaveId){
            try {
                leaveRepository.deleteById(leaveId);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
}

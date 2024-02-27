package com.example.employee.service;

import com.example.employee.model.LeaveType;
import com.example.employee.repository.LeaveRepository;
import com.example.employee.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeService {
    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    LeaveRepository leaveRepository;

    public ResponseEntity<List<LeaveType>> allLeaveType(){
        List<LeaveType> leaveTypeList=leaveTypeRepository.findAll();
        if(leaveTypeList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(leaveTypeList));
    }

    public ResponseEntity<Void> addNewLeaveType(LeaveType leaveType){
        try {
            LeaveType newLeaveType=leaveTypeRepository.save(leaveType);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> updateLeaveType(LeaveType leaveType){
        try {
            LeaveType updatedLeaveType=leaveTypeRepository.save(leaveType);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteLeaveType(int leaveTypeId){
        if((leaveRepository.findAllByLeaveTypeId(leaveTypeId)).size()== 0) {
            try {
                leaveTypeRepository.deleteById(leaveTypeId);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

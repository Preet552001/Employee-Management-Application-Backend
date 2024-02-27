package com.example.employee.service;

import com.example.employee.model.Department;
import com.example.employee.model.Designation;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.repository.DesignationRepository;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {

    @Autowired
    DesignationRepository designationRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Designation>> allDesignation(){
        List<Designation> departmentList=designationRepository.findAll();
        if(departmentList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(departmentList));
    }

    public ResponseEntity<Void> addNewDesignation(Designation designation){
        if((departmentRepository.findById(designation.getDepartmentId()).orElse(new Department())).getId()== 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Designation newDesignation=designationRepository.save(designation);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> updateDesignation(Designation designation){
        if((departmentRepository.findById(designation.getDepartmentId()).orElse(new Department())).getId()== 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Designation updateDesignation=designationRepository.save(designation);
                return ResponseEntity.status(HttpStatus.OK).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> deleteDesignation(int designationId){
        if((employeeRepository.findAllByDesignationId(designationId)).size()== 0) {
            try {
                designationRepository.deleteById(designationId);
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

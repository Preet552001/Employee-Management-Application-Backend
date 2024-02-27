package com.example.employee.service;

import com.example.employee.model.Department;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DesignationRepository designationRepository;

    public ResponseEntity<List<Department>> allDepartment(){
        List<Department> departmentList=departmentRepository.findAll();
        if(departmentList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(departmentList));
    }

    public ResponseEntity<Void> addNewDepartment(Department department){
        try {
            Department newDepartment=departmentRepository.save(department);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> updateDepartment(Department department){
        try {
            Department updatedDepartment=departmentRepository.save(department);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteDepartment(int departmentId){
        if((designationRepository.findAllByDepartmentId(departmentId)).size()== 0) {
            try {
                departmentRepository.deleteById(departmentId);
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

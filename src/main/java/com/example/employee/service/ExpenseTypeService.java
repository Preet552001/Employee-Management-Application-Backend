package com.example.employee.service;

import com.example.employee.model.ExpenseType;
import com.example.employee.repository.ExpenseRepository;
import com.example.employee.repository.ExpenseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseTypeService {
    @Autowired
    ExpenseTypeRepository expenseTypeRepository;
    @Autowired
    ExpenseRepository expenseRepository;

    public ResponseEntity<List<ExpenseType>> allExpenseType(){
        List<ExpenseType> expenseTypes=expenseTypeRepository.findAll();
        if(expenseTypes.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(expenseTypes));
    }

    public ResponseEntity<Void> addNewExpenseType(ExpenseType expenseType){
        try {
            ExpenseType newExpenseType=expenseTypeRepository.save(expenseType);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> updateExpenseType(ExpenseType expenseType){
        try {
            ExpenseType updatedExpenseType=expenseTypeRepository.save(expenseType);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteExpenseType(int expenseTypeId){
        if((expenseRepository.findAllByExpenseTypeId(expenseTypeId)).size()== 0) {
            try {
                expenseTypeRepository.deleteById(expenseTypeId);
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

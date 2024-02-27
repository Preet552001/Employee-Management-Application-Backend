package com.example.employee.service;

import com.example.employee.model.*;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.ExpenseRepository;
import com.example.employee.repository.ExpenseTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    public ResponseEntity<List<Expense>> allExpense(){
        List<Expense> expenseList=expenseRepository.findAll();
        if(expenseList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(expenseList));
    }

    public ResponseEntity<List<Expense>> allExpenseByEmployeeId(int employeeId){
        List<Expense> expenseList=expenseRepository.findAllByEmployeeId(employeeId);
        if(expenseList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(expenseList));
    }

    public ResponseEntity<Void> addExpense(Expense expense){
        if( (employeeRepository.findById(expense.getEmployeeId()).orElse(new Employee())).getId()== 0  || (expenseTypeRepository.findById(expense.getExpenseTypeId()).orElse(new ExpenseType())).getId() == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Expense newExpense=expenseRepository.save(expense);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> updateExpense(Expense expense){
        if( (employeeRepository.findById(expense.getEmployeeId()).orElse(new Employee())).getId()== 0  || (expenseTypeRepository.findById(expense.getExpenseTypeId()).orElse(new ExpenseType())).getId() == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Expense newExpense=expenseRepository.save(expense);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> deleteExpense(int expenseId){
        try {
            expenseRepository.deleteById(expenseId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

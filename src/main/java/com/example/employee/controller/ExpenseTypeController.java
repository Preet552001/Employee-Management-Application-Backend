package com.example.employee.controller;

import com.example.employee.model.Department;
import com.example.employee.model.ExpenseType;
import com.example.employee.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenseType")
public class ExpenseTypeController {

    @Autowired
    ExpenseTypeService expenseTypeService;

    @GetMapping
    private ResponseEntity<?> getExpenseType(){
        return expenseTypeService.allExpenseType();
    }

    @PostMapping
    private ResponseEntity<?> postExpenseType(ExpenseType expenseType){
        return expenseTypeService.addNewExpenseType(expenseType);
    }

    @PutMapping
    private ResponseEntity<?> putExpenseType(ExpenseType expenseType){
        return expenseTypeService.updateExpenseType(expenseType);
    }

    @DeleteMapping(path = "{expenseTypeId}")
    private ResponseEntity<?> deleteExpenseType(@PathVariable("expenseTypeId")int expenseTypeId){
        return expenseTypeService.deleteExpenseType(expenseTypeId);
    }
}

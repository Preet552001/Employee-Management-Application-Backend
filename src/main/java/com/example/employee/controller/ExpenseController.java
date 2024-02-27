package com.example.employee.controller;

import com.example.employee.model.Expense;
import com.example.employee.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<?> getExpense(){
        return expenseService.allExpense();
    }

    @GetMapping(path = "employee/{employeeId}")
    public ResponseEntity<?> getExpenseByEmployeeId(@PathVariable("employeeId") int employeeId){
        return expenseService.allExpenseByEmployeeId(employeeId);
    }

    @PostMapping
    public ResponseEntity<?> postExpense(Expense expense){
        return expenseService.addExpense(expense);
    }

    @PutMapping
    public ResponseEntity<?> putExpense(Expense expense){
        return expenseService.addExpense(expense);
    }

    @DeleteMapping(path = "{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable("expenseId") int expenseId){
        return expenseService.deleteExpense(expenseId);
    }
}

package com.example.employee.repository;

import com.example.employee.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findAllByExpenseTypeId(int expenseTypeId);

    List<Expense> findAllByEmployeeId(int employeeId);

    void deleteAllByEmployeeId(int employeeId);
}

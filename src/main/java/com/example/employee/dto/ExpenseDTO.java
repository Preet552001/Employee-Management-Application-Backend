package com.example.employee.dto;

import com.example.employee.model.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseDTO {
    private int id;
    private Date date;
    private float cost;
    private String status;
    private int approverId;
    private ExpenseType expenseType;
    private String comment;
}

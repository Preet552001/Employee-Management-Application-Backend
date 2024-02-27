package com.example.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "emp_expense")
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Date")
    private Date date;

    @Column(name = "Cost")
    private float cost;

    @Column(name = "Status")
    private String status;

    @Column(name = "EmployeeId")
    private int employeeId;

    @Column(name = "ApproverId")
    private int approverId;

    @Column(name = "ExpenseTypeId")
    private int expenseTypeId;

    @Column(name = "Comment")
    private String comment;

}

package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int probation;
    private float salary;
    private Date hireDate;
    private int managerId;
    private DesignationDTO designation;
    private AccountDetailDTO accountDetail;
    private List<LeaveDTO> leaves;
    private List<ExpenseDTO> expense;
}

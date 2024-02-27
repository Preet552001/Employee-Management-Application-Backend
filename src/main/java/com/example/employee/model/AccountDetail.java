package com.example.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "emp_accountdetail")
@Entity
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "BankName")
    private String bankName;

    @Column(name = "IfciCode")
    private String ifciCode;

    @Column(name = "Branch")
    private String branch;

    @Column(name = "NameOnAccount")
    private String nameOnAccount;

    @Column(name = "AccountNumber",columnDefinition="")
    private String accountNumber;

    @Column(name = "EmployeeId")
    private int employeeId;
}

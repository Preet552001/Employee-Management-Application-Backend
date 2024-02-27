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
@Table(name = "emp_leave")
@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "LeaveTypeId")
    private int leaveTypeId;

    @Column(name = "EmployeeId")
    private int employeeId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FromDate")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ToDate")
    private Date toDate;

    @Column(name = "Note")
    private String note;

    @Column(name = "ApproverId")
    private int approverId;

    @Column(name = "Approved")
    private int approved;

    @Column(name = "Status")
    private String status;
}

package com.example.employee.dto;

import com.example.employee.model.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveDTO {
    private int id;
    private LeaveType leaveType;
    private Date fromDate;
    private Date toDate;
    private String note;
    private int approverId;
    private int approved;
    private String status;
}

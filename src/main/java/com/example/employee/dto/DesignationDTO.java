package com.example.employee.dto;

import com.example.employee.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DesignationDTO {
    private int id;
    private String title;
    private Department department;
}

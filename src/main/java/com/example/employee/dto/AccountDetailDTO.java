package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDetailDTO {
    private int id;
    private String bankName;
    private String ifciCode;
    private String branch;
    private String nameOnAccount;
    private String accountNumber;
}

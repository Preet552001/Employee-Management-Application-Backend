package com.example.employee.controller;

import com.example.employee.model.AccountDetail;
import com.example.employee.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountDetail")
public class AccountDetailController {
    @Autowired
    AccountDetailService accountDetailService;

    @GetMapping
    public ResponseEntity<?> getAccountDetail(){
        return accountDetailService.allAccountDetail();
    }

    @GetMapping(path = "employee/{employeeId}")
    public ResponseEntity<?> getAccountDetailByEmployeeId(@PathVariable("employeeId") int employeeId){
        return accountDetailService.accountDetailByEmployeeId(employeeId);
    }

    @PostMapping
    public ResponseEntity<?> postAccountDetail(AccountDetail accountDetail){
        return accountDetailService.addNewAccountDetail(accountDetail);
    }

    @PutMapping
    public ResponseEntity<?> putAccountDetail(AccountDetail accountDetail){
        return accountDetailService.updateAccountDetail(accountDetail);
    }

    @DeleteMapping(path = "{accountDetailId}")
    public ResponseEntity<?> deleteAccountDetail(@PathVariable("accountDetailId") int accountDetailId){
        return accountDetailService.deleteAccountDetail(accountDetailId);
    }
}

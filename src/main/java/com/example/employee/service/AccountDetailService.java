package com.example.employee.service;

import com.example.employee.model.AccountDetail;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.repository.AccountDetailRepository;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDetailService {
    @Autowired
    AccountDetailRepository accountDetailRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<AccountDetail>> allAccountDetail(){
        List<AccountDetail> accountDetailList=accountDetailRepository.findAll();
        if(accountDetailList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(accountDetailList));
    }

    public ResponseEntity<AccountDetail> accountDetailByEmployeeId(int employeeId){
        AccountDetail accountDetail=accountDetailRepository.findByEmployeeId(employeeId).orElse(new AccountDetail());
        if(accountDetail.getId() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(accountDetail));
    }

    public ResponseEntity<Void> addNewAccountDetail(AccountDetail accountDetail){
        if((employeeRepository.findById(accountDetail.getEmployeeId()).orElse(new Employee())).getId()== 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                AccountDetail newAccountDetail=accountDetailRepository.save(accountDetail);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> updateAccountDetail(AccountDetail accountDetail){
        if((employeeRepository.findById(accountDetail.getEmployeeId()).orElse(new Employee())).getId()== 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                AccountDetail updateAccountDetail=accountDetailRepository.save(accountDetail);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> deleteAccountDetail(int accountDetailId){
        try {
            accountDetailRepository.deleteById(accountDetailId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

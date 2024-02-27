package com.example.employee.service;

import com.example.employee.dto.*;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DesignationRepository  designationRepository;

    @Autowired
    AccountDetailRepository accountDetailRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList=employeeRepository.findAll();
        if(employeeList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(employeeList));
    }

    public ResponseEntity<EmployeeDTO> employeeById(int employeeId){
        try {
            EmployeeDTO employeeDTO=new EmployeeDTO();
            Employee employee=employeeRepository.findById(employeeId).orElse(new Employee());
            if(employee.getId() == 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else {
                employeeDTO.setId(employeeId);
                employeeDTO.setFirstName(employee.getFirstName());
                employeeDTO.setLastName(employee.getLastName());
                employeeDTO.setBirthDate(employee.getBirthDate());
                employeeDTO.setProbation(employee.getProbation());
                employeeDTO.setSalary(employee.getSalary());
                employeeDTO.setHireDate(employee.getHireDate());
                employeeDTO.setManagerId(employee.getManagerId());

                DesignationDTO designationDTO=new DesignationDTO();
                Designation designation=designationRepository.findById(employee.getDesignationId()).orElse(new Designation());
                designationDTO.setId(designation.getId());
                designationDTO.setTitle(designation.getTitle());
                Department department=departmentRepository.findById(designation.getDepartmentId()).orElse(new Department());
                designationDTO.setDepartment(department);
                employeeDTO.setDesignation(designationDTO);

                AccountDetailDTO accountDetailDTO=new AccountDetailDTO();
                AccountDetail accountDetail =accountDetailRepository.findByEmployeeId(employeeId).orElse(new AccountDetail());
                accountDetailDTO.setId(accountDetail.getId());
                accountDetailDTO.setBankName(accountDetail.getBankName());
                accountDetailDTO.setIfciCode(accountDetail.getIfciCode());
                accountDetailDTO.setBranch(accountDetail.getBranch());
                accountDetailDTO.setNameOnAccount(accountDetail.getNameOnAccount());
                accountDetailDTO.setAccountNumber(accountDetail.getAccountNumber());
                employeeDTO.setAccountDetail(accountDetailDTO);

                List<Expense> expenseList=expenseRepository.findAllByEmployeeId(employeeId);
                ArrayList<ExpenseDTO> employeeDTOList=new ArrayList<ExpenseDTO>();
                expenseList.forEach(expense -> {
                    ExpenseDTO expenseDTO=new ExpenseDTO();
                    expenseDTO.setId(expense.getId());
                    expenseDTO.setDate(expense.getDate());
                    expenseDTO.setCost(expense.getCost());
                    expenseDTO.setStatus(expense.getStatus());
                    ExpenseType expenseType=expenseTypeRepository.findById(expense.getExpenseTypeId()).orElse(new ExpenseType());
                    expenseDTO.setExpenseType(expenseType);
                    expenseDTO.setComment(expense.getComment());
                    employeeDTOList.add(expenseDTO);
                });
                employeeDTO.setExpense(employeeDTOList);


                List<Leave> leaveList=leaveRepository.findAllByEmployeeId(employeeId);
                ArrayList<LeaveDTO> leaveDTOList=new ArrayList<LeaveDTO>();
                leaveList.forEach(leave -> {
                    LeaveDTO leaveDTO=new LeaveDTO();
                    leaveDTO.setId(leave.getId());
                    LeaveType leaveType=leaveTypeRepository.findById(leave.getLeaveTypeId()).orElse(new LeaveType());
                    leaveDTO.setLeaveType(leaveType);
                    leaveDTO.setFromDate(leave.getFromDate());
                    leaveDTO.setToDate(leave.getToDate());
                    leaveDTO.setNote(leave.getNote());
                    leaveDTO.setApproverId(leave.getApproverId());
                    leaveDTO.setApproved(leave.getApproved());
                    leaveDTO.setStatus(leave.getStatus());
                    leaveDTOList.add(leaveDTO);
                });
                employeeDTO.setLeaves(leaveDTOList);
                return ResponseEntity.of(Optional.of(employeeDTO));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> addNewEmployee(Employee employee){
        if((designationRepository.findById(employee.getDesignationId()).orElse(new Designation())).getId()== 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Employee newEmployee=employeeRepository.save(employee);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> updateEmployee(Employee employee){
        if((designationRepository.findById(employee.getDesignationId()).orElse(new Designation())).getId()== 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else {
            try {
                Employee updateEmployee=employeeRepository.save(employee);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    public ResponseEntity<Void> deleteEmployee(int employeeId){
        try {
            AccountDetail accountDetail=accountDetailRepository.findByEmployeeId(employeeId).orElse(new AccountDetail());
            if(accountDetail.getId()!=0){
                accountDetailRepository.deleteById(accountDetail.getId());
            }
            List<Expense> expenseList=expenseRepository.findAllByEmployeeId(employeeId);
            if(expenseList.size() !=0){
                expenseList.forEach(expense -> {
                    expenseRepository.deleteById(expense.getId());
                });
            }
            List<Leave> leaveList = leaveRepository.findAllByEmployeeId(employeeId);
            if(leaveList.size() !=0){
                leaveList.forEach(leave -> {
                    leaveRepository.deleteById(leave.getId());
                });
            }
            employeeRepository.deleteById(employeeId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

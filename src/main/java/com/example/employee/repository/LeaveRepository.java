package com.example.employee.repository;

import com.example.employee.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {
    List<Leave> findAllByLeaveTypeId(int leaveTypeId);

    List<Leave> findAllByEmployeeId(int employeeId);

    void deleteAllByEmployeeId(int employeeId);
}

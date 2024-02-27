package com.example.employee.repository;

import com.example.employee.model.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Integer> {
    Optional<AccountDetail> findByEmployeeId(int employeeId);

//    void deleteByEmployeeId(int employeeId);
}

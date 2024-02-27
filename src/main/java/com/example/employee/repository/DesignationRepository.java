package com.example.employee.repository;

import com.example.employee.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Integer> {
    List<Designation> findAllByDepartmentId(int departmentId);
}

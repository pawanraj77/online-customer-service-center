package com.company.onlinecustomerservicecenter.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    @Query("SELECT d FROM Department d WHERE d.deptName = :deptName")
    Optional<Department> findByName(String deptName);

}

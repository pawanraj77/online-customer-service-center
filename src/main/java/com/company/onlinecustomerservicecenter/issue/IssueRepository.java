package com.company.onlinecustomerservicecenter.issue;

import com.company.onlinecustomerservicecenter.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
}

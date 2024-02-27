package com.company.onlinecustomerservicecenter.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.onlinecustomerservicecenter.employee.Employee;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Optional<Issue> findByDescription(String description);

}

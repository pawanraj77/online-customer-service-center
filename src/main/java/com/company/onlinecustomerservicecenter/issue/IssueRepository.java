package com.company.onlinecustomerservicecenter.issue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Optional<Issue> findByIssueId(Integer issueId);
    Optional<Issue> findByDescription(String description);
}

 

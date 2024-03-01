package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.issue.Issue;

import java.util.List;

public interface OperatorService {
    public Operator createAnOperator(Operator operator)throws OperatorException;

    Issue addIssue(Issue issue);

    Operator assignIssue(Integer operatorId, Integer issueId)throws OperatorException;

    List<Operator> getAllOperator();

    List<Issue> getAllIssues();

    Operator issueSolved(Integer opertaorId,Integer issueId) throws OperatorException;

    Boolean deleteOperator(Integer id)throws OperatorException;

    Integer remainingIssuesByOperator(Integer operatorId)throws OperatorException;

    List<Operator> getAllOperatorsByDept(Integer id) throws OperatorException;
    
    List<Issue> assignedIssues(Integer id)throws OperatorException;

    Operator assignOperatorToDept(Integer operatorId, Integer deptId)throws OperatorException;
}

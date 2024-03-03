package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.department.Department;
import com.company.onlinecustomerservicecenter.department.DepartmentRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class OperatorApplicationTests
{
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private DepartmentRepository departmentRepo;
    @Test
    @DisplayName("Create an Operator")
    void createAnOperator() throws OperatorException {
        Operator createOperator=new Operator(4, "chandu", "m", "mchandu250202@gmail.com", "Chandu25@#", "9668052524", "gunupur", 0, null, null);
        Operator operator=null;
        try {
            operator=this.operatorService.createAnOperator(createOperator);
            Assertions.assertNotNull(operator);
        }catch (OperatorException e)
        {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("Assign department to Operator")
    void assignOperatorToDept()throws OperatorException{
        Operator operator=null;
        try {
            Optional<Department>departmentOpt = this.departmentRepo.findById(1);
            Department department=departmentOpt.get();
            Optional<Operator>operatorOpt=this.operatorRepository.findById(52);
            Operator operator1=operatorOpt.get();
            operator=this.operatorService.assignOperatorToDept(operator1.getOperatorId(),department.getDeptId());
            Assertions.assertNotNull(operator);
        }catch (OperatorException e)
        {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("To find All operators by department id")
    void getAllOperatorsByDeptID()
    {
        List<Operator>operatorList=null;
        try{
            operatorList=this.operatorService.getAllOperatorsByDept(1);
            Assertions.assertNotNull(operatorList);

        } catch (OperatorException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("Issues to be solved by operator")
    void getRemainingIssuesByOperator()
    {
        Integer remainingIssues=null;
        try{
            remainingIssues=this.operatorService.remainingIssuesByOperator(1);
            Assertions.assertNotNull(remainingIssues);
        } catch (OperatorException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("To assign issues to the operator")
    void assignIssuesToSolve()
    {
        Operator operator=null;
        try {
            operator=this.operatorService.assignIssue(1,1);
            Assertions.assertNotNull(operator);
        }catch (OperatorException e)
        {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("Operator solved the issues")
    void operatorSolved()
    {
        Operator operator=null;
        try {
            operator=this.operatorService.issueSolved(1,1);
            Assertions.assertNotNull(operator);
        }catch (OperatorException e)
        {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("To get all issues assigned to operator")
    void allAssignedIssues()
    {
        List<Issue>issueList=null;
        try{
            issueList=this.operatorService.assignedIssues(1);
            Assertions.assertNotNull(issueList);
        } catch (OperatorException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

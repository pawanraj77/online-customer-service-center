package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.*;
import com.company.onlinecustomerservicecenter.department.Department;
import com.company.onlinecustomerservicecenter.department.DepartmentRepository;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private IssueBucketRepository issueBucketRepository;
    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public Operator createAnOperator(Operator operator)throws OperatorException
    {
        Optional<Operator>operatorOpt=this.operatorRepository.findById(operator.getOperatorId());
        if(operatorOpt.isPresent())
            throw new OperatorException("The operator is already present!...");
        Optional<Operator>operatorOpt1=this.operatorRepository.findByEmail(operator.getEmail());
        if(operatorOpt1.isPresent())
            throw new OperatorException("This email already exists try with new Email!...");
        IssueBucket issueBucket=new IssueBucket();
        issueBucket=this.issueBucketRepository.save(issueBucket);
        operator.setIssueBucket(issueBucket);
        operator=this.operatorRepository.save(operator);
        return operator;
    }

    @Override
    public Issue addIssue(Issue issue) {
        return this.issueRepository.save(issue);
    }

    @Override
    public Operator assignIssue(Integer operatorId, Integer issueId) throws OperatorException {
        Optional<Operator>operatorOPt=this.operatorRepository.findById(operatorId);
        if(operatorOPt.isEmpty())
            throw new OperatorException("The operatoe doesnt exists!....");
        Optional<Issue>issueOpt=this.issueRepository.findById(issueId);
        if(issueOpt.isEmpty())
            throw new OperatorException("The issues doesnt exists but our operators will be working on it!....");
        Operator operator=operatorOPt.get();
        Issue issue=issueOpt.get();
        operator.getIssueBucket().getIssues().add(issue);
        return this.operatorRepository.save(operator);
    }

    @Override
    public List<Operator> getAllOperator() {
        return this.operatorRepository.findAll();
    }

    @Override
    public List<Issue> getAllIssues() {
        return this.issueRepository.findAll();
    }

    @Override
    public Operator issueSolved(Integer opertaorId, Integer issueId)throws OperatorException {
        Optional<Operator>operatorOpt=this.operatorRepository.findById(opertaorId);
        if(operatorOpt.isEmpty())
            throw new OperatorException("There is no operator exists!...");
        Optional<Issue>issueOpt=this.issueRepository.findById(issueId);
        if(issueOpt.isEmpty())
            throw new OperatorException("There is no such issues to be solved....");
        Operator operator=operatorOpt.get();
        Issue issue=issueOpt.get();
        operator.getIssueBucket().getIssues().remove(issue);
        operator.setIssuesSolved(operator.getIssuesSolved()+1);
        return this.operatorRepository.save(operator);
    }

    @Override
    public Boolean deleteOperator(Integer id) throws OperatorException{
        Optional<Operator>operatorOpt=this.operatorRepository.findById(id);
        if(operatorOpt.isEmpty())
            throw new OperatorException("There is no operator to remove!.....");
        this.operatorRepository.delete(operatorOpt.get());
        return true;
    }

    @Override
    public Integer remainingIssuesByOperator(Integer operatorId)throws OperatorException {
        Optional<Operator>operatorOpt=this.operatorRepository.findById(operatorId);
        if(operatorOpt.isEmpty())
            throw new OperatorException("There is no operator to show the issues!....");
        Operator operator=operatorOpt.get();
        return operator.getIssueBucket().getIssues().size();

    }

    @Override
    public List<Operator> getAllOperatorsByDept(Integer id)throws OperatorException {
        Optional<Department>departmentOpt=this.departmentRepo.findById(id);
        if(departmentOpt.isEmpty())
            throw new OperatorException("There is no department with this....");
        Department department=departmentOpt.get();
        List<Operator>operatorList=department.getOperators();
        return operatorList;

    }

    @Override
    public Operator assignOperatorToDept(Integer operatorId, Integer deptId) throws OperatorException{
        Optional<Operator>operatorOpt=this.operatorRepository.findById(operatorId);
        if(operatorOpt.isEmpty())
            throw new OperatorException("operator is not present to assign it to the department!...");
        Optional<Department>departmentOpt=this.departmentRepo.findById(deptId);
        if(departmentOpt.isEmpty())
            throw new OperatorException("There is no department to add operator");
        Operator operator=operatorOpt.get();
        Department department=departmentOpt.get();
        operator.setDepartment(department);
        operator=this.operatorRepository.save(operator);
        department.getOperators().add(operator);
        this.departmentRepo.save(department);
        return operator;
    }
     @Override
    public List<Issue> assignedIssues(Integer id)throws OperatorException {
        Optional<Operator>operatorOpt=this.operatorRepository.findById(id);
        if(operatorOpt.isEmpty())
            throw new OperatorException("Please check your id and reenter....");
        Operator operator=operatorOpt.get();
        List<Issue>allIssues=operator.getIssueBucket().getIssues();
        return allIssues;
    }
}

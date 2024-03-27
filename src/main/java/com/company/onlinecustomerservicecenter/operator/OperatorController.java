package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.onlinecustomerservicecenter.issue.Issue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4800/")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @PostMapping("create/operator")
    public Operator createOperator(@RequestBody Operator operator) throws OperatorException {
        return this.operatorService.createAnOperator(operator);
    }
    @PutMapping("addOperatorToDept/{operatorId}/{deptId}")
    public Operator assignOperatorToDept(@PathVariable("operatorId") Integer operatorId,@PathVariable("deptId") Integer deptId) throws OperatorException {
        return this.operatorService.assignOperatorToDept(operatorId,deptId);
    }

    @PostMapping("add/issues")
    public Issue addIssue(@RequestBody Issue issue)
    {
        return this.operatorService.addIssue(issue);
    }
    @PutMapping("assign/issue/{operatorId}/{issueId}")
    public Operator assignIssueToOperator(@PathVariable("operatorId")Integer operatorId,@PathVariable("issueId") Integer issueId) throws OperatorException {
        return this.operatorService.assignIssue(operatorId,issueId);
    }
    @GetMapping("operator/all")
    public List<Operator> getAllOperator()
    {
        return this.operatorService.getAllOperator();
    }
//    @GetMapping("issues/all")
//    public  List<Issue> getAllIssues()
//    {
//        return this.operatorService.getAllIssues();
//    }
   @PutMapping("issueSolved/inCart/{operatorId}/{issueId}")
    public Operator issueSolved(@PathVariable("operatorId") Integer operatorId,@PathVariable("issueId") Integer issueId) throws OperatorException {
        return this.operatorService.issueSolved(operatorId,issueId);
    }
    @DeleteMapping("operator/{id}")
    public Operator deleteOperator(@PathVariable("id") Integer id) throws OperatorException {
        return this.operatorService.deleteOperator(id);
    }
    @GetMapping("remainingIssue/{operatorId}")
    public Integer remainingIssuesByOperator(@PathVariable("operatorId")Integer operatorId) throws OperatorException {
        return this.operatorService.remainingIssuesByOperator(operatorId);
    }
    @GetMapping("getOperatorsById/{deptId}")
    public List<Operator> getOperatorByDeptId(@PathVariable("deptId") Integer id) throws OperatorException {
        return this.operatorService.getAllOperatorsByDept(id);
    }
    @PostMapping("/operator/Login")
    public Operator operatorLogin(@RequestBody LoginDto loginDto)throws  OperatorException
    {
        return this.operatorService.operatorLogin(loginDto.getEmail(),loginDto.getPassword());
    }
    @GetMapping("/issueBucket/{operatorId}")
    public List<Issue>assignedIssues(@PathVariable("operatorId") Integer id)throws OperatorException
    {
        return this.operatorService.assignedIssues(id);
    }
    @PostMapping("update/operator")
    public Operator updateOperator(@RequestBody Operator operator)throws OperatorException
    {
        return this.operatorService.updateOperator(operator);
    }
    @GetMapping("getOperator/{operatorId}")
    public Operator getOperator(@PathVariable("operatorId")Integer id) throws OperatorException
    {
        return this.operatorService.getOperator(id);
    }


}


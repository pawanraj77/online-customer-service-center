package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeController(EmployeeService employeeService, IssueService issueService){
        this.employeeService = employeeService;
        this.issueService = issueService;
    }
    private EmployeeService employeeService;
    private IssueService issueService;

    @PostMapping("register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

    @PostMapping("create/issue")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Issue addIssue(@RequestBody Issue issue) throws EmployeeException{
        return this.employeeService.addNewIssue(issue);
    }

    @GetMapping("employee/{cdsId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer cdsId) throws EmployeeException{
        return this.employeeService.getEmployeeById(cdsId);
    }

    @PostMapping("/login")
    public Employee login(@RequestBody LoginDto loginDto) throws EmployeeException {
        return this.employeeService.employeelogin(loginDto.getEmail(), loginDto.getPassword());
    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestBody LoginDto loginDto) throws EmployeeException{
        return this.employeeService.changePassword(loginDto);

    }

    @GetMapping("/forgetPassword/{cdsId}")
    public Employee forgetPassword(@PathVariable("cdsId") Integer id) throws EmployeeException{
        return this.employeeService.forgetPassword(id);
    }

    @GetMapping("/viewIssuesByEmployee/{csId}")
    public List<Issue> viewIssuesByCustomer(@PathVariable Integer csId) throws EmployeeException{

        return this.employeeService.viewIssuesByCustomer(csId);
    }

    @PostMapping("raiseIssueByEmployee/{cdsId}/{issueId}")
    public Employee raiseIssueByCustomer(@PathVariable("cdsId") Integer cdsId, @PathVariable("issueId") Integer issueId) throws EmployeeException {
        return this.employeeService.raiseIssue(cdsId, issueId);
    }







}

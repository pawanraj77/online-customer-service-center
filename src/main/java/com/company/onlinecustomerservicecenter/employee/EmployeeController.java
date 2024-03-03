package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    private EmployeeService employeeService;

    @PostMapping("register/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

    @GetMapping("employees")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Employee> getAllEmployees() throws EmployeeException {
        return this.employeeService.getAllEmployees();
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

    @GetMapping("/viewIssues/{csId}")
    public List<Issue> viewIssuesByCustomer(@PathVariable Integer csId) throws EmployeeException{

        return this.employeeService.viewIssuesByCustomer(csId);
    }

    @PostMapping("raiseIssue/{cdsId}/{issueId}")
    public String raiseIssueByEmployee(@PathVariable("cdsId") Integer cdsId, @PathVariable("issueId") Integer issueId) throws EmployeeException {
        return this.employeeService.raiseIssue(cdsId, issueId);
    }









}

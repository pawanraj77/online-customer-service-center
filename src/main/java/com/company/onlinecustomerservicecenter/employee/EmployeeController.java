package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4800/")
//@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    private EmployeeService employeeService;

    @PostMapping("register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

    @GetMapping("employees")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Employee> getAllEmployees() throws EmployeeException {
        return this.employeeService.getAllEmployees();
    }

    @DeleteMapping("employee/{cdsId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee deleteEmployeeById(@PathVariable("cdsId") Integer cdsId) throws EmployeeException {
        return this.employeeService.deleteEmployeeById(cdsId);
    }

    @PutMapping("employee")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee) throws EmployeeException {
        return this.employeeService.updateEmployee(employee);
    }

//    @PostMapping("create/issue")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public Issue addIssue(@RequestBody Issue issue) throws EmployeeException{
//        return this.employeeService.addNewIssue(issue);
//    }

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
    public Employee changePassword(@RequestBody LoginDto loginDto) throws EmployeeException{
        return this.employeeService.changePassword(loginDto);

    }

    @GetMapping("/forgetPassword/{cdsId}")
    public Employee forgetPassword(@PathVariable("cdsId") Integer id) throws EmployeeException{
        return this.employeeService.forgetPassword(id);
    }

    @GetMapping("/viewIssues/{cdsId}")
    public List<Issue> viewIssuesByEmployee(@PathVariable Integer cdsId) throws EmployeeException{

        return this.employeeService.viewIssues(cdsId);
    }

    @PostMapping("raiseIssue/{cdsId}/{description}")
    public Employee raiseIssueByEmployee(@PathVariable("cdsId") Integer cdsId, @PathVariable("description") String issueDescription) throws EmployeeException {
        return this.employeeService.raiseIssue(cdsId, issueDescription);
    }









}

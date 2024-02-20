package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("register")
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

    @GetMapping("employee/{cdsId}")
    public Employee getEmployeeById(@PathVariable Integer cdsId) throws EmployeeException{
        return this.employeeService.getEmployeeById(cdsId);
    }

    @PostMapping("account/login")
    public Employee login(@RequestBody LoginDto loginDto) throws EmployeeException {
        return this.employeeService.employeelogin(loginDto.getEmail(), loginDto.getPassword());
    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestBody LoginDto loginDto) throws EmployeeException{
        return this.employeeService.changePassword(loginDto);

    }

    @GetMapping("/forgetPassword/{id}")
    public Employee forgetPassword(@PathVariable Integer id) throws EmployeeException{
        return this.employeeService.forgetPassword(id);
    }

    @GetMapping("/viewIssue/{id}/{key}")
    public Issue viewIssue(@PathVariable Integer id, @PathVariable String key){
        return this.employeeService.viewIssue(id, key);
    }




}

package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;

import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee) throws EmployeeException;

    Employee getEmployeeById(Integer cdsId) throws EmployeeException;

    List<Issue> viewIssues(Integer csId) throws EmployeeException;

    Employee forgetPassword(Integer id) throws EmployeeException;


    Employee employeelogin(String email, String password) throws EmployeeException;

    Employee changePassword(LoginDto loginDto) throws EmployeeException;

    Employee raiseIssue(Integer cdsId, String issueDescription) throws EmployeeException;

//    Issue addNewIssue(Issue issue) throws EmployeeException;

    List<Employee> getAllEmployees();

    Employee deleteEmployeeById(Integer cdsId) throws EmployeeException;

    Employee updateEmployee(Employee employee) throws EmployeeException;
}

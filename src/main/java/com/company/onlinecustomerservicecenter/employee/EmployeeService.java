package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;

import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee) throws EmployeeException;

    Employee getEmployeeById(Integer cdsId) throws EmployeeException;

    List<Issue> viewIssuesByCustomer(Integer csId) throws EmployeeException;

    Employee forgetPassword(Integer id) throws EmployeeException;


    Employee employeelogin(String email, String password) throws EmployeeException;

    String changePassword(LoginDto loginDto) throws EmployeeException;

    String raiseIssue(Integer cdsId, Integer issueId) throws EmployeeException;

    Issue addNewIssue(Issue issue) throws EmployeeException;

    List<Employee> getAllEmployees();
}

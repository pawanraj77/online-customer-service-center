/****************************************************************************************************
 *          @author          Pawan Raj
 *          Description      It is a service class that provides the services for managing employees.
 *                           This includes adding a new employee,delete employee,update employee,
 *                           getting an employee, login an employee, getting all the employees, change password,
 *                           fprget password, raise issue and view issues raised by employee
 *                        .
 *          Version          3.2.2
 *          Created Date     10-feb-2024
 *****************************************************************************************************/

package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.solution.Solution;

import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee) throws EmployeeException;

    Employee getEmployeeById(Integer cdsId) throws EmployeeException;

    List<Issue> viewIssues(Integer csId) throws EmployeeException;

    Employee forgetPassword(Integer id) throws EmployeeException;


    Employee employeelogin(String email, String password) throws EmployeeException;

    Employee changePassword(LoginDto loginDto) throws EmployeeException;

    Employee raiseIssue(Integer cdsId, String issueDescription) throws EmployeeException;


    List<Employee> getAllEmployees();

    Employee deleteEmployeeById(Integer cdsId) throws EmployeeException;

    Employee updateEmployee(Employee employee) throws EmployeeException;

}

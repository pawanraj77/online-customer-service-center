package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee) throws EmployeeException;

    Employee getEmployeeById(Integer cdsId) throws EmployeeException;

    Issue viewIssue(Integer id, String key);

    Employee forgetPassword(Integer id);


    Employee employeelogin(String email, String password);

    String changePassword(LoginDto loginDto);
}

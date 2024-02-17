package com.ford.onlinecustomerservicecenter.employee;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee) throws EmployeeException;

    Employee getEmployeeById(Integer cdsId) throws EmployeeException;
}

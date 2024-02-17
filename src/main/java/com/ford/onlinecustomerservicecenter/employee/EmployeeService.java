package com.ford.onlinecustomerservicecenter.employee;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee);

    Employee getEmployeeById(Integer cdsId);
}

package com.company.onlinecustomerservicecenter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addNewEmployee(Employee employee) throws EmployeeException{
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer cdsId) throws EmployeeException{
        return this.employeeRepository.findById(cdsId).get();
    }


}

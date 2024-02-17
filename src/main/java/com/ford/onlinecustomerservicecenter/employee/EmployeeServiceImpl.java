package com.ford.onlinecustomerservicecenter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addNewEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer cdsId) {
        return this.employeeRepository.findById(cdsId).get();
    }


}

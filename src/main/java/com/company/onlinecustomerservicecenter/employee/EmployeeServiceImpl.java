package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Employee addNewEmployee(Employee employee) throws EmployeeException{
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer cdsId) throws EmployeeException{
        return this.employeeRepository.findById(cdsId).get();
    }

    @Override
    public Issue viewIssue(Integer id, String key) throws EmployeeException{
        return this.issueRepository.findById(id).get();
    }

    @Override
    public Employee forgetPassword(Integer id) {
        Employee employee = this.employeeRepository.findById(id).get();
        employee.setPassword("praj16");
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee employeelogin(String userEmail, String userPassword) {
        Optional<Employee> employeeOpt = this.employeeRepository.findByEmail(userEmail);
        if(employeeOpt.isEmpty())
            throw new EmployeeException("Employee does not exists for : "+userEmail);

        Employee foundEmployee = employeeOpt.get();
        if(!foundEmployee.getPassword().equals(userPassword))
            throw new EmployeeException("Password does not match");

        return foundEmployee;
    }

    @Override
    public String changePassword(LoginDto loginDto) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(loginDto.getEmail());

        if(employeeOpt.isEmpty()) {
            throw new EmployeeException("Invalid Credentials");
        }

        Employee employeeFound = employeeOpt.get();
        employeeFound.setPassword(loginDto.getPassword());
        employeeRepository.save(employeeFound);
        return "Password Changed Successfully";
    }


}

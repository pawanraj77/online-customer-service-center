package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueException;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, IssueRepository issueRepository) {
        this.employeeRepository = employeeRepository;
        this.issueRepository = issueRepository;
    }


    private EmployeeRepository employeeRepository;
    private IssueRepository issueRepository;

    @Override
    public Employee addNewEmployee(Employee newEmployee) throws EmployeeException{
        if(newEmployee==null)
            throw new EmployeeException("Employee can't be null");

        Optional<Employee> employeeOpt = this.employeeRepository.findByEmail(newEmployee.getEmail());
        if(employeeOpt.isPresent())
            throw new EmployeeException("EmailId already exists: "+ newEmployee.getEmail());

        return this.employeeRepository.save(newEmployee);
    }

    @Override
    public Employee getEmployeeById(Integer cdsId) throws EmployeeException{
        Optional<Employee> foundEmployee = this.employeeRepository.findById(cdsId);
        if(foundEmployee.isPresent())
            return foundEmployee.get();
        else
            throw new EmployeeException("Employee Id not found: " + cdsId);
    }


    @Override
    public Employee forgetPassword(Integer id) throws EmployeeException{
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);
        if(employeeOpt.isEmpty()){
            throw new EmployeeException("Employee not found for this id: "+ id);
        }
        Employee employee = employeeOpt.get();
        employee.setPassword("#76pth89");
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee employeelogin(String userEmail, String userPassword) throws EmployeeException{
        Optional<Employee> employeeOpt = this.employeeRepository.findByEmail(userEmail);
        if(employeeOpt.isEmpty())
            throw new EmployeeException("Employee does not exists for : "+userEmail);

        Employee foundEmployee = employeeOpt.get();
        if(!foundEmployee.getPassword().equals(userPassword))
            throw new EmployeeException("Password does not match");

        return foundEmployee;
    }

    @Override
    public Employee changePassword(LoginDto loginDto) throws EmployeeException{
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(loginDto.getEmail());

        if(employeeOpt.isEmpty()) {
            throw new EmployeeException("Invalid Credentials");
        }

        Employee employeeFound = employeeOpt.get();
        employeeFound.setPassword(loginDto.getPassword());
        employeeRepository.save(employeeFound);
        return employeeFound;
    }

//    @Override
//    public Issue addNewIssue(Issue issue) throws EmployeeException{
//        Optional<Issue> issueOpt = this.issueRepository.findByDescription(issue.getDescription());
//        if(issueOpt.isPresent())
//            throw new EmployeeException("Issue already exists, Add new issue");
//
//        return this.issueRepository.save(issue);
//    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException{
        List<Employee> employees = this.employeeRepository.findAll();
        if(employees.isEmpty())
        {
            throw new EmployeeException("No Employees exists, add employees!!!");
        }
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee deleteEmployeeById(Integer cdsId) throws EmployeeException{
        Optional<Employee> employeeOpt = this.employeeRepository.findById(cdsId);
        if(employeeOpt.isPresent()){
            this.employeeRepository.deleteById(cdsId);
        }
        else {
            throw new EmployeeException("No Employee Exists, with cdsId: "+ cdsId);
        }

        return employeeOpt.get();
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeException {
        if(employee == null){
            throw new EmployeeException("Employee not exists, Can't update!!!");
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Issue> viewIssues(Integer cdsId) throws EmployeeException{
        Optional<Employee> employeeOpt = this.employeeRepository.findById(cdsId);
        if(employeeOpt.isEmpty()) {
            throw new EmployeeException("Employee cdsId not found: "+ cdsId);
        }
        Employee employee = employeeOpt.get();
        if(employee.getIssues()==null)
        {
            throw new EmployeeException("Employee not raised any issue");
        }
        else{
            return employee.getIssues();
        }
    }


    @Override
    public Employee raiseIssue(Integer cdsId, String issueDescription) throws EmployeeException{
        Optional<Employee> employeeOpt = this.employeeRepository.findById(cdsId);
        Optional<Issue> issueOpt = this.issueRepository.findByDescription(issueDescription);

        if(employeeOpt.isEmpty()){
            throw new EmployeeException("Employee Id not found: "+ cdsId);
        }

        Employee employee = employeeOpt.get();

        if(issueOpt.isEmpty())
        {
            Issue newIssue = new Issue();
            newIssue.setDescription(issueDescription);
            newIssue.setIssueType("Not found");
            newIssue = this.issueRepository.save(newIssue);
            employee.getIssues().add(newIssue);
            newIssue.setEmployee(employee);
            employee = this.employeeRepository.save(employee);

        }
        else {
            Issue issue = issueOpt.get();
            issue.setEmployee(employee);
            issue = this.issueRepository.save(issue);
            employee.getIssues().add(issue);
            employee = this.employeeRepository.save(employee);
        }

        return employee;
    }


}

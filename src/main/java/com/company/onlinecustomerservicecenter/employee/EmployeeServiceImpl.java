package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, IssueRepository issueRepository) {

    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
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
    public String changePassword(LoginDto loginDto) throws EmployeeException{
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(loginDto.getEmail());

        if(employeeOpt.isEmpty()) {
            throw new EmployeeException("Invalid Credentials");
        }

        Employee employeeFound = employeeOpt.get();
        employeeFound.setPassword(loginDto.getPassword());
        employeeRepository.save(employeeFound);
        return "Password Changed Successfully";
    }

    @Override
    public Issue addNewIssue(Issue issue) throws EmployeeException{
        Optional<Issue> issueOpt = this.issueRepository.findByDescription(issue.getDescription());
        if(issueOpt.isPresent())
            throw new EmployeeException("Issue already exists, Add new issue");

        return this.issueRepository.save(issue);
    }

    @Override
    public List<Issue> viewIssuesByCustomer(Integer cdsId) throws EmployeeException{
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
    public Employee raiseIssue(Integer cdsId, Integer issueId) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(cdsId);
        Optional<Issue> issueOpt = this.issueRepository.findById(issueId);

        if(employeeOpt.isEmpty()){
            throw new EmployeeException("Employee Id not found: "+ cdsId);
        }
        if (issueOpt.isEmpty()) {
            throw new EmployeeException("Issue Id not found: "+ issueId);
        }

        Employee employee = employeeOpt.get();
        Issue issue = issueOpt.get();

        issue.setEmployee(employee);
        employee.addIssue(issue);

        this.employeeRepository.save(employee);
        this.issueRepository.save(issue);

        return employee;

    }


}

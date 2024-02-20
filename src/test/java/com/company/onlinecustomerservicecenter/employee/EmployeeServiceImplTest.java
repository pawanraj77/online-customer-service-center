package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IssueRepository issueRepository;


    @Test
    @DisplayName(value = "add employee to the repository")
    void addNewEmployeeTest() throws EmployeeException{

        Employee employee = null;
        Employee employee1 = null;
        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "5667",  "chennai", null));
            Assertions.assertNotNull(employee);

            employee1 = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "praj16@gmail", "8976",  "chennai", null));

            Assertions.assertNotEquals(employee.getEmail(), employee1.getEmail(), "Cannot add employee1, its email already present");
        }
        catch (EmployeeException e){
            Assertions.fail(e.getMessage());
        }

        employeeRepository.delete(employee);
        employeeRepository.delete(employee1);
    }

    @Test
    @DisplayName(value = "get employee by cdsId")
    void getEmployeeByIdTest() throws EmployeeException{

        Employee employee = null;
        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "5667",  "chennai", null));

            employee = this.employeeService.getEmployeeById(employee.getCdsId());
            Assertions.assertNotNull(employee);
        }
        catch (EmployeeException e){
            Assertions.fail(e.getMessage());
        }

        employeeRepository.delete(employee);
    }

    @Test
    @DisplayName(value = "forget password by using cdsId")
    void forgetPasswordTest() throws EmployeeException {

        Employee employee = null;
        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "5667", "chennai", null));

            employee = this.employeeService.forgetPassword(employee.getCdsId());

            assertEquals("#76pth89", employee.getPassword());
        }
        catch (EmployeeException e) {
            Assertions.fail(e.getMessage());
        }

        employeeRepository.delete(employee);
    }

    @Test
    @DisplayName(value = "employee login, checks the authentication")
    void employeeLoginTest() throws EmployeeException {
        Employee employee = null;
        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "pkl$$5667", "chennai", null));

            employee = this.employeeService.employeelogin(employee.getEmail(), employee.getPassword());

            String userEmail = "pawan@gmail";
            String userPassword = "pkl$$5667";

            assertEquals(userEmail, employee.getEmail());
            assertEquals(userPassword, employee.getPassword());
        }
        catch (EmployeeException e) {
            Assertions.fail(e.getMessage());
        }

        employeeRepository.delete(employee);
    }

    @Test
    @DisplayName(value = "change password by using EmailId")
    void changePasswordTest() throws EmployeeException {
        Employee employee = null;
        LoginDto loginDto = null;
        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "pkl$$5667", "chennai", null));

            loginDto = new LoginDto("pawan@gmail", "kossj##987", "software");

            Optional<Employee> employeeOpt = this.employeeRepository.findByEmail(loginDto.getEmail());

            employee = employeeOpt.get();

            String result = employeeService.changePassword(loginDto);
            employee.setPassword(loginDto.getPassword());
            assertEquals("Password Changed Successfully", result);
            assertEquals("kossj##987", employee.getPassword());

        }
        catch (EmployeeException e) {
            Assertions.fail(e.getMessage());
        }
        employeeRepository.delete(employee);
    }

    @Test
    @DisplayName(value = "add not null issue")
    void addNewIssueNotNullTest() throws EmployeeException{
        Issue issue = null;
        try {
            issue = this.employeeService.addNewIssue(new Issue(2, "software", "how to stop lagging", null, null, null));
            Assertions.assertNotNull(issue);
        }
        catch (EmployeeException e){
            Assertions.fail(e.getMessage());
        }
        issueRepository.delete(issue);
    }

    @Transactional
    @Test
    @DisplayName(value = "raise issue for employee")
    void raiseIssueByEmployeeTest() throws EmployeeException {

        Employee employee = null;
        Issue issue = null;

        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "pkl$$5667", "chennai", null));
            issue = this.employeeService.addNewIssue(new Issue(1, "software",
                    "how to stop lagging", null, null, null));

            Employee result = this.employeeService.raiseIssue(employee.getCdsId(), issue.getIssueId());

            Assertions.assertEquals(employee, result);

        }
        catch (EmployeeException e) {
            Assertions.fail(e.getMessage());
        }

        employeeRepository.delete(employee);
        issueRepository.delete(issue);
    }

    @Test
    @DisplayName(value = "view issue By particular employee id")
    void viewNotNullIssueByEmployeeTest() {
        Employee employee = null;
        Issue issue = null;

        try {
            employee = this.employeeService.addNewEmployee(new Employee(1, "pawan", "raj",
                    "98839393", "pawan@gmail", "pkl$$5667", "chennai", null));
            issue = this.employeeService.addNewIssue(new Issue(1, "software",
                    "how to stop lagging", null, null, null));

            this.employeeService.raiseIssue(1, 1);

            employeeRepository.save(employee);
            issueRepository.save(issue);

            List<Issue> issues = this.employeeService.viewIssuesByCustomer(employee.getCdsId());

            Assertions.assertNotNull(issues);

        } catch (EmployeeException e) {
            Assertions.fail(e.getMessage());
        }
        employeeRepository.delete(employee);
        issueRepository.delete(issue);

    }
}

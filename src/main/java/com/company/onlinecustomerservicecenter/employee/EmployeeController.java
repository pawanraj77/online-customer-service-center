/*************************************************************************************
 *          @author          Pawan Raj
 *          Description      It is a controller class that handles HTTP requests for
 *                           department-related operations.This includes adding new
 *                           employee, retrieving employee by ID, retrieving all
 *                           employeess, deleting employee by ID, updating
 *                           employee, employee login, changing password, forget password,
 *                           raising issue by employee, viewing issues by employee.
 *
 *
 *          Version          3.2.2
 *          Created Date     10-feb-2024
 *************************************************************************************/
package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.dto.LoginDto;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.solution.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4800/")
//@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    private EmployeeService employeeService;

/************************************************************************************
 * Method: 			            - registerNewEmployee
 * Description: 			    - For registering the new employee
 * @param employee              - employee to add; must not be null and must be valid
 * @return employee             - return the registered employee
 * @throws EmployeeException    - if an error occurs during the registration of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024

 ***********************************************************************************/
    @PostMapping("employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

/************************************************************************************
 * Method: 			            - getAllEmployees
 * Description: 			    - For getting all the employees
 * @return employee             - return all the employee which is present in database
 * @throws EmployeeException    - if an error occurs during getting all the employees
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/
    @GetMapping("employees")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Employee> getAllEmployees() throws EmployeeException {
        return this.employeeService.getAllEmployees();
    }

/************************************************************************************
 * Method: 			            - deleteEmployeeById
 * Description: 			    - For deleting the employee by id
 * @param cdsId                 - employee cdsId
 * @return employee             - return the deleted employee
 * @throws EmployeeException    - if an error occurs during the deletion of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024

 ***********************************************************************************/
    @DeleteMapping("employee/{cdsId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee deleteEmployeeById(@PathVariable("cdsId") Integer cdsId) throws EmployeeException {
        return this.employeeService.deleteEmployeeById(cdsId);
    }

/************************************************************************************
 * Method: 			            - updateEmployee
 * Description: 			    - For update the employee by id
 * @param employee              - employee to update
 * @return employee             - return the updated employee
 * @throws EmployeeException    - if an error occurs during the update of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/
    @PutMapping("employee")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee) throws EmployeeException {
        return this.employeeService.updateEmployee(employee);
    }

/************************************************************************************
 * Method: 			            - getEmployeeById
 * Description: 			    - For getting the employee by id
 * @param cdsId                 - employee cdsId
 * @return employee             - return the employee
 * @throws EmployeeException    - if an error occurs during the getting of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/
    @GetMapping("employee/{cdsId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer cdsId) throws EmployeeException{
        return this.employeeService.getEmployeeById(cdsId);
    }

/************************************************************************************
 * Method: 			            - login
 * Description: 			    - For login the employee
 * @param loginDto              - employee login
 * @return employee             - return the login employee
 * @throws EmployeeException    - if an error occurs during the login of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/

    @PostMapping("employee/login")
    public Employee login(@RequestBody LoginDto loginDto) throws EmployeeException {
        return this.employeeService.employeelogin(loginDto.getEmail(), loginDto.getPassword());
    }

/************************************************************************************
 * Method: 			            - changePassword
 * Description: 			    - For changing the password of the employee
 * @param loginDto              - employee login
 * @return employee             - return the employee after changing the password
 * @throws EmployeeException    - if an error occurs during the password changing of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/

    @PutMapping("employee/changePassword")
    public Employee changePassword(@RequestBody LoginDto loginDto) throws EmployeeException{
        return this.employeeService.changePassword(loginDto);

    }

/************************************************************************************
 * Method: 			            - forgetPassword
 * Description: 			    - For forget the password by employee cdsId
 * @param id                    - employee cdsId
 * @return employee             - return the employee after adding new password
 * @throws EmployeeException    - if an error occurs during the forget password of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/
    @GetMapping("employee/forgetPassword/{cdsId}")
    public Employee forgetPassword(@PathVariable("cdsId") Integer id) throws EmployeeException{
        return this.employeeService.forgetPassword(id);
    }

/************************************************************************************
 * Method: 			            - viewIssuesByEmployee
 * Description: 			    - For viewing or getting the issues of employee by id
 * @param cdsId                 - employee cdsId
 * @return employee             - return the list of issues raised by employee
 * @throws EmployeeException    - if an error occurs during getting the issues of employee
 * Created By                   - Pawan Raj
 * Created Date                 - 10-feb-2024
 ***********************************************************************************/
    @GetMapping("employee/issues/{cdsId}")
    public List<Issue> viewIssuesByEmployee(@PathVariable Integer cdsId) throws EmployeeException{

        return this.employeeService.viewIssues(cdsId);
    }

/************************************************************************************
 * Method: 			                - raiseIssueByEmployee
 * Description: 			        - For raising the issue by employee using cdsId and issue description
 * @param cdsId,issueDescription    - employee cdsId and issue description
 * @return employee                 - return the employee after raising the issue
 * @throws EmployeeException        - if an error occurs during the raising of issue by employee
 * Created By                       - Pawan Raj
 * Created Date                     - 10-feb-2024

 ***********************************************************************************/
    @PostMapping("employee/issue/{cdsId}/{description}")
    public Employee raiseIssueByEmployee(@PathVariable("cdsId") Integer cdsId, @PathVariable("description") String issueDescription) throws EmployeeException {
        return this.employeeService.raiseIssue(cdsId, issueDescription);
    }


}

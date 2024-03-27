/*************************************************************************************
 *          @author          Jayshree
 *          Description      It is a controller class that handles HTTP requests for
 *                           department-related operations.This includes adding a new
 *                           department, retrieving a department by ID,retrieving all
 *                           departments, deleting a department by ID, and updating a
 *                           department.
 *          Version          3.2.2
 *          Created Date     10-feb-2024
 *************************************************************************************/

package com.company.onlinecustomerservicecenter.department;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4800/")
public class DepartmentController {

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private DepartmentService departmentService;

/************************************************************************************
 * Method: 			        - addDepartment
 * Description: 			- For creating the new department
 * @param d                 - department to add; must not be null and must be valid
 * @return ResponseEntity   - containing the added department
 * @throws DepartmentException- if an error occurs during the addition of the department
 * Created By                - Jayshree
 * Created Date              - 10-feb-2024

 ***********************************************************************************/
    @PostMapping("add/department")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department d) throws DepartmentException{
    Department addedDepartment = this.departmentService.addDepartment(d);
    return ResponseEntity.ok(addedDepartment);
}

    /************************************************************************************
     * Method: 			        - getDepartmentById
     * Description: 			- For getting the department by id
     * @param id                - department Id
     * @return department       - return the particular department
     * @throws DepartmentException- if an error occurs during getting the department
     * Created By                - Jayshree
     * Created Date              - 10-feb-2024

     ************************************************************************************/

    @GetMapping("department/{id}")
    public Department getDepartmentById(@PathVariable("id") Integer id) throws DepartmentException{
        return this.departmentService.getDepartmentById(id);
    }

    /************************************************************************************
     * Method: 			        - getAllDepartment
     * Description: 			- For getting all the department
     * @return department       - return the all the department which is present in the database
     * @throws DepartmentException- if an error occurs during getting the department
     * Created By                - Jayshree
     * Created Date              - 10-feb-2024

     ************************************************************************************/

    @GetMapping("departments")
    public List<Department> getAllDepartment() throws DepartmentException{
        return this.departmentService.getAllDepartment();
    }

    /************************************************************************************
     * Method: 			        - deleteDepartmentById
     * Description: 			- For deleting the department by id
     * @param id                - department Id
     * @return department       - return the deleted department
     * @throws DepartmentException- if an error occurs during getting the department
     * Created By                - Jayshree
     * Created Date              - 10-feb-2024

     ************************************************************************************/

    @DeleteMapping("department/{id}")
    public Department deleteDepartmentById(@PathVariable("id") Integer id) throws DepartmentException{
        return this.departmentService.deleteDepartmentById(id);
    }


    /************************************************************************************
     * Method: 			        - updateDepartment
     * Description: 			- For updating the department
     * @param department        - department to update; must not be null and must be valid
     * @return department       - return the updated department
     * @throws DepartmentException- if an error occurs during getting the department
     * Created By                - Jayshree
     * Created Date              - 10-feb-2024

     ************************************************************************************/

    @PutMapping("department")
    public Department updateDepartment(@Valid @RequestBody Department department) throws DepartmentException {
        return this.departmentService.updateDepartment(department);
    }
}

/****************************************************************************************************
 *          @author          Jayshree
 *          Description      It is a service class that provides the services for managing departments.
 *                           This includes adding a new department,delete department,update department
 *                           and viewing all the department in the system.
 *          Version          3.2.2
 *          Created Date     10-feb-2024
 *****************************************************************************************************/

package com.company.onlinecustomerservicecenter.department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department d)throws DepartmentException;

    Department getDepartmentById(Integer id) throws DepartmentException;

    List<Department> getAllDepartment() throws DepartmentException;

    Department deleteDepartmentById(Integer id) throws DepartmentException;


    Department updateDepartment(Department department) throws DepartmentException;
}

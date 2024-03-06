package com.company.onlinecustomerservicecenter.department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department d)throws DepartmentException;

    Department getDepartmentById(Integer id) throws DepartmentException;

    List<Department> getAllDepartment() throws DepartmentException;

    Department deleteDepartmentById(Integer id) throws DepartmentException;


    Department updateDepartment(Department department) throws DepartmentException;
}

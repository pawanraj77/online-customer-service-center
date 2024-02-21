package com.company.onlinecustomerservicecenter.department;


import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department d);

    Department getDepartmentById(Integer id);

    List<Department> getAllDepartment();

    Department deleteDepartmentById(Integer id);

}

package com.company.onlinecustomerservicecenter.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
 class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
     void addDepartmentTest(){
        Department department = new Department(1,"software",null);
        try {
            department = this.departmentService.addDepartment(department);
            Assertions.assertNotNull(department);
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }

        this.departmentRepository.delete(department);
    }



    @Test
    void getDepartmentByIdTest(){
        Department department = new Department(2,"Hardware",null);
        departmentRepository.save(department);
        try {
            department = this.departmentService.getDepartmentById(department.getDeptId());
            Assertions.assertNotNull(department);
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }
    }




    @Test
    void getAllDepartmentTest(){
        Department department1 = new Department(1,"Hardware",null);
        Department department2 = new Department(2,"Software",null);
        Department department3 = new Department(3,"IT",null);
        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);

        List<Department> allDepartment = null;
        try {
            allDepartment = this.departmentService.getAllDepartment();
            Assertions.assertNotNull(allDepartment);
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void deleteDepartmentByIdTest(){
        Department department = new Department(1,"HR",null);
        departmentRepository.save(department);
        try {
            department = this.departmentService.deleteDepartmentById(department.getDeptId());
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }

    }
}

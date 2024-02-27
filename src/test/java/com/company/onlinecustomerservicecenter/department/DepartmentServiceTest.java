package com.company.onlinecustomerservicecenter.department;

import com.company.onlinecustomerservicecenter.operator.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
 class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    void getDepartmentByIdTest() {
        Department department = new Department(2, "Hardware", null);
        departmentRepository.save(department);
        try {
            department = this.departmentService.getDepartmentById(department.getDeptId());
            Assertions.assertNotNull(department);
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }
    }


    @Test
    void getDepartmentWithNegativeId() {
        assertThrows(DepartmentException.class, () -> {
            Department department = this.departmentService.getDepartmentById(-1);
        });
    }



    @Test
    void getDepartmentByNonExistingId(){
        Department newDepartment = new Department(1, "IT", null);
        departmentRepository.save(newDepartment);

        int nonExistingId = 6;
        assertThrows(DepartmentException.class, () -> {
            Department department = departmentService.getDepartmentById(nonExistingId);
            assertNull(department, "Expected department to be null for non-existing ID");
        });
    }


    @Test
    void addDepartmentTest() {
        Department department = new Department(1, "software", null);
        try {
            department = this.departmentService.addDepartment(department);
            Assertions.assertNotNull(department);
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }

        this.departmentRepository.delete(department);
    }


    @Test
    void addDepartmentTest1(){
        Department department = new Department(1, "HR", null);
        departmentRepository.save(department);

        try {
            Department result = this.departmentService.getDepartmentById(department.getDeptId());

            Assertions.assertEquals(department.getDeptId(), result.getDeptId());
            Assertions.assertEquals(department.getDeptName(), result.getDeptName());

        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }
    }



    @Test

    void addDepartmentWithInvalidId() {
        Department existingDepartment = new Department(1, "HR", null);
        departmentRepository.save(existingDepartment);
        Assertions.assertThrows(DepartmentException.class,()->departmentService.getDepartmentById(88));
    }


    @Test
    void getAllDepartmentTest() {
        Department department1 = new Department(1, "Hardware", null);
        Department department2 = new Department(2, "Software", null);
        Department department3 = new Department(3, "IT", null);
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
    void NullGetAllDepartmentTest(){

        try {
            List<Department>  allDepartment = departmentService.getAllDepartment();
        } catch (DepartmentException e) {
            Assertions.assertEquals("No deparment found",e.getMessage());
        }
    }

    @Test
    void deleteDepartmentByIdTest() {
        Department department = new Department(1, "HR", null);
        departmentRepository.save(department);
        try {
            department = this.departmentService.deleteDepartmentById(department.getDeptId());
        } catch (DepartmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void deleteDepartmentInvalidIdTest(){
        Department department = new Department(1, "HR", null);
        departmentRepository.save(department);
        try {
            department = this.departmentService.deleteDepartmentById(80);
        } catch (DepartmentException e) {
            Assertions.assertEquals("This Department is not exist : 80",e.getMessage());
        }
    }

}




package com.company.onlinecustomerservicecenter.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("add/department")
    public Department addDepartment(Department d){
        return this.departmentService.addDepartment(d);
    }

    @GetMapping("department/{id}")
    public Department getDepartmentById(@PathVariable("id") Integer id){
        return this.departmentService.getDepartmentById(id);
    }

    @GetMapping("departments")
    public List<Department> getAllDepartment(){
        return this.departmentService.getAllDepartment();
    }

    @DeleteMapping("department/{id}")
    public Department deleteDepartmentById(@PathVariable("id") Integer id){
        return this.departmentService.deleteDepartmentById(id);
    }


}

package com.company.onlinecustomerservicecenter.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department addDepartment(Department d) {
        return this.departmentRepository.save(d);

    }

    @Override
    public Department getDepartmentById(Integer id) {
       Optional<Department>optionalDepartment=  this.departmentRepository.findById(id);
       return optionalDepartment.get();
    }

    @Override
    public List<Department> getAllDepartment() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department deleteDepartmentById(Integer id) {
        Optional<Department>optionalDepartment =  this.departmentRepository.findById(id);
        Department d1 = optionalDepartment.get();
         this.departmentRepository.deleteById(id);

        return d1;
    }


}

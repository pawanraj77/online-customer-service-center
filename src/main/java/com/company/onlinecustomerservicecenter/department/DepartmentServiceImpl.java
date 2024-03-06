package com.company.onlinecustomerservicecenter.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department d) throws DepartmentException{
        Optional<Department> optionalDepartment = this.departmentRepository.findById(d.getDeptId());
        if (optionalDepartment.isPresent())
            throw new DepartmentException("This department is already present");
        return this.departmentRepository.save(d);

    }

    @Override
    public Department getDepartmentById(Integer id) throws DepartmentException{
       Optional<Department>optionalDepartment=  this.departmentRepository.findById(id);
       if (optionalDepartment.isEmpty())
           throw new DepartmentException("This Department is not exist"+ id);
       return optionalDepartment.get();
    }

    @Override
    public List<Department> getAllDepartment()throws DepartmentException {
        if (this.departmentRepository.findAll().isEmpty())
            throw new DepartmentException("There is no department present");
        return this.departmentRepository.findAll();
    }

    @Override
    public Department deleteDepartmentById(Integer id) throws DepartmentException {
        Optional<Department>optionalDepartment =  this.departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            throw new DepartmentException("This Department is not exist : "+ id);

        Department d1 = optionalDepartment.get();
        this.departmentRepository.deleteById(id);

        return d1;
    }

    @Override
    public Department updateDepartment(Department department) throws DepartmentException{
        Optional<Department> optionalDepartment = this.departmentRepository.findById(department.getDeptId());
        if (optionalDepartment.isEmpty())
            throw new DepartmentException("This department does not exist");
        return this.departmentRepository.save(department);
    }


}

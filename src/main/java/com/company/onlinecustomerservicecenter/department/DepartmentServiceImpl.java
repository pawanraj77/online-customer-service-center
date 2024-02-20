package com.company.onlinecustomerservicecenter.department;

import com.company.onlinecustomerservicecenter.operator.Operator;
import com.company.onlinecustomerservicecenter.operator.OperatorRepository;
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

    @Autowired
    private OperatorRepository operatorRepository;
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
        if (this.departmentRepository.findAll().size() <= 0)
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



}

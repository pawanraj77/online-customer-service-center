package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.department.Department;
import com.company.onlinecustomerservicecenter.department.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OperatorServiceTest
{
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private DepartmentRepository departmentRepo;
    @Test
    @DisplayName("Create an Operator")
    void createAnOperator() throws OperatorException {
        Operator createOperator=new Operator(4, "chandu", "m", "mchandu250202@gmail.com", "Chandu25@#", "9668052524", "gunupur", 0, null, null);
        Operator operator=null;
        try {
            operator=this.operatorService.createAnOperator(createOperator);
            Assertions.assertNotNull(operator);
        }catch (OperatorException e)
        {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("To find All operators by department id")
    void getAllOperatorsByDeptID()
    {
        List<Operator> operatorList=null;
        try{
            operatorList=this.operatorService.getAllOperatorsByDept(1);
            Assertions.assertNotNull(operatorList);

        } catch (OperatorException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

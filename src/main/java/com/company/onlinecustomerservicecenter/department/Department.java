package com.company.onlinecustomerservicecenter.department;

import com.company.onlinecustomerservicecenter.operator.Operator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


/*****************************************************************************************
 *          @author          Jayshree
 *          Description      It is a POJO class that represents a department in the system.
 *                           department have depId and department name.
 *          Version             3.2.2
 *          Created Date      10-feb-2024
 *****************************************************************************************/



@Entity
public class Department {

    @Id
    @GeneratedValue
    private Integer deptId;

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters long")
    private String deptName;


    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Operator> operators=new ArrayList<>();
    public Department() {
    }


    public Department(Integer deptId,String deptName, List<Operator> operators) {

        super();
        this.deptId = deptId;
        this.deptName = deptName;
        this.operators = operators;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }


}

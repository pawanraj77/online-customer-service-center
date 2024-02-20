package com.company.onlinecustomerservicecenter.department;

import com.company.onlinecustomerservicecenter.operator.Operator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Integer deptId;
    private String deptName;

    @OneToMany
    private List<Operator> operators=new ArrayList<>();

    public Department(Integer deptId, String deptName, List<Operator> operators) {
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

    public Department() {
    }
}

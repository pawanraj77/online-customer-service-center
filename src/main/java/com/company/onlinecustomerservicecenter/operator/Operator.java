package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.department.Department;
import com.company.onlinecustomerservicecenter.issue.Issue;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Operator {
    @Id
    @GeneratedValue
    private Integer operatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    private String city;

    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @ManyToOne
    private Department department;

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Operator(Integer operatorId, String firstName, String lastName, String email, String password, String phoneNo, String city, List<Issue> issues, Department department) {
        this.operatorId = operatorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.city = city;
        this.issues = issues;
        this.department = department;
    }

    public Operator() {
        super();
    }
}
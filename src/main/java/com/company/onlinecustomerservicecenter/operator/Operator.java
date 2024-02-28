package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.department.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.company.onlinecustomerservicecenter.entity.Department;
import com.company.onlinecustomerservicecenter.issue.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    private Integer issuesSolved;

    @OneToOne
    private IssueBucket issueBucket;
    @JsonIgnore
    @ManyToOne
    @JsonIgnore
    private Department department;
    public Operator() {
    }

    public Operator(String firstName, String lastName, String email, String password, String phoneNo, String city, Integer issuesSolved, IssueBucket issueBucket, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.city = city;
        this.issuesSolved = issuesSolved;
        this.issueBucket = issueBucket;
        this.department = department;
    }

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

    public Integer getIssuesSolved() {
        return issuesSolved;
    }
      
// =======
//     public List<Issue> getIssues() {
//         return issues;
//     }

//     public void setIssues(List<Issue> issues) {
//         this.issues = issues;
//     }

//     public Department getDepartment() {
//         return department;
//        }
// >>>>>>> main

    public void setIssuesSolved(Integer issuesSolved) {
        this.issuesSolved = issuesSolved;
    }

    public IssueBucket getIssueBucket() {
        return issueBucket;
    }

    public void setIssueBucket(IssueBucket issueBucket) {
        this.issueBucket = issueBucket;
    }

    public Department getDepartment() {
        return department;
    }
//     public Operator(Integer operatorId, String firstName, String lastName, String email, String password, String phoneNo, String city, List<Issue> issues, Department department) {
//         super();
//         this.operatorId = operatorId;
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//         this.password = password;
//         this.phoneNo = phoneNo;
//         this.city = city;
//         this.issues = issues;
//         this.department = department;
// >>>>>>> main
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

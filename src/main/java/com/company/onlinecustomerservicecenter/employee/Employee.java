package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.issue.Issue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer cdsId;
    private String firstName;
    private String lastName;
    private Long phoneNo;
    private String email;
    private String password;
    private String city;

//    private Boolean isAdmin = false;

    @OneToMany
    private Map<Integer, Issue> issues = new HashMap<>();

    public Employee(Integer cdsId, String firstName, String lastName, Long phoneNo,
                    String email, String password, String city, Map<Integer, Issue> issues) {
        super();
        this.cdsId = cdsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.city = city;
        this.issues = issues;
    }

    public Integer getCdsId() {
        return cdsId;
    }

    public void setCdsId(Integer cdsId) {
        this.cdsId = cdsId;
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

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<Integer, Issue> getIssues() {
        return issues;
    }

    public void setIssues(Map<Integer, Issue> issue) {
        this.issues = issues;
    }

    public Employee() {
        super();
    }
}

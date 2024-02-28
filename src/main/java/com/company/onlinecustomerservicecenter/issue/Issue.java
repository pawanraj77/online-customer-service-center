package com.company.onlinecustomerservicecenter.issue;

import com.company.onlinecustomerservicecenter.employee.Employee;
import com.company.onlinecustomerservicecenter.solution.Solution;
import com.company.onlinecustomerservicecenter.operator.Operator;
import jakarta.persistence.*;

@Entity
public class Issue {
    @Id
    @GeneratedValue
    private Integer issueId;
    private String issueType;
    private String description;

    @OneToOne
    private Solution solution;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Operator operator;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Issue(Integer issueId, String issueType, String description, Solution solution, Employee employee, Operator operator) {
        this.issueId = issueId;
        this.issueType = issueType;
        this.description = description;
        this.solution = solution;
        this.employee = employee;
        this.operator = operator;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Issue() {
    }
}

package com.company.onlinecustomerservicecenter.entity;

import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.operator.Operator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
@Entity
public class Solution {
    @Id
    @GeneratedValue
    private Integer solutionId;
    private String description;
    private LocalDate date;

    @OneToOne
    private Issue issue;

//    @OneToOne
//    private Operator operator;

    public Solution(Integer solutionId, String description, LocalDate date, Issue issue, Operator operator) {
        this.solutionId = solutionId;
        this.description = description;
        this.date = date;
        this.issue = issue;
//        this.operator = operator;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

//    public Operator getOperator() {
//        return operator;
//    }
//
//    public void setOperator(Operator operator) {
//        this.operator = operator;
//    }

    public Solution() {
    }
}

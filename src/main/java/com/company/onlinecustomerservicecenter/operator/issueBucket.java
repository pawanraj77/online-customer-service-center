package com.company.onlinecustomerservicecenter.operator;

import com.company.onlinecustomerservicecenter.issue.Issue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class IssueBucket {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany
    private List<Issue> issues=new ArrayList<>();

    public IssueBucket() {
    }

    public IssueBucket(List<Issue> issues) {
        this.issues = issues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}

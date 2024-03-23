package com.company.onlinecustomerservicecenter.solution;

import com.company.onlinecustomerservicecenter.issue.Issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



import java.time.LocalDate;
@Entity
public class Solution {
    @Id
    @GeneratedValue
    private Integer solutionId;

    @NotEmpty(message = "Description cannot be empty,it is a required field!")
    @Size(min = 5,message = "Description should have 5 characters atleast!")
    private String description;


    @NotNull(message = "Date cannot be null")
    private LocalDate date;


    @JsonIgnore
    @OneToOne
    private Issue issue;


    public Solution() {
    }

    public Solution(Integer solutionId, String description, LocalDate date, Issue issue) {
        this.solutionId = solutionId;
        this.description = description;
        this.date = date;
        this.issue = issue;
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
}

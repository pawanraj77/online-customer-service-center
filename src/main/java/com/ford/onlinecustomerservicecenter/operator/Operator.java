package com.app.customerservice.operator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Operator {
    @Id
    @GeneratedValue
    private Integer operatorId;
    private String operatorName;
    private Integer issuesSolvedByOperator;
    private Integer pendingIssues;

    public Operator() {
    }

    public Operator(String operatorName, Integer issuesSolvedByOperator, Integer pendingIssues) {
        this.operatorName = operatorName;
        this.issuesSolvedByOperator = issuesSolvedByOperator;
        this.pendingIssues = pendingIssues;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getIssuesSolvedByOperator() {
        return issuesSolvedByOperator;
    }

    public void setIssuesSolvedByOperator(Integer issuesSolvedByOperator) {
        this.issuesSolvedByOperator = issuesSolvedByOperator;
    }

    public Integer getPendingIssues() {
        return pendingIssues;
    }

    public void setPendingIssues(Integer pendingIssues) {
        this.pendingIssues = pendingIssues;
    }
}

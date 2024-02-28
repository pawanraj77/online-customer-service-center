package com.company.onlinecustomerservicecenter.issue;
import com.company.onlinecustomerservicecenter.solution.Solution;

import java.util.List;

public interface IssueService {
    Issue createIssue(Issue issue) throws IssueException;

    Issue updateIssue(Integer issueId, String issueDescription, Solution issueSolution) throws IssueException;

    Issue deleteIssue(Integer issueId) throws IssueException;

    Issue closeIssue(Integer issueId)  throws IssueException;

    Issue reOpenIssue(Integer issueId, String issueDescription)  throws IssueException;

    Issue viewIssue(Integer issueId) throws IssueException;

    List<Issue> getAllIssues() throws IssueException;
}

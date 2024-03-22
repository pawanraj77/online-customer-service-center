package com.company.onlinecustomerservicecenter.issue;

import java.util.List;

public interface IssueService {
    Issue addIssue(Issue issue);

    List<Issue> getAllIssues();
}

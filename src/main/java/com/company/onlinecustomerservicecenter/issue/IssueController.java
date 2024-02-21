package com.company.onlinecustomerservicecenter.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

    private final IssueService issueService;
     @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("add")
    public Issue addIssue(Issue issue){
        return this.issueService.addIssue(issue);
    }

}

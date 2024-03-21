package com.company.onlinecustomerservicecenter.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin("http://localhost:4200/")
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

    @GetMapping("issues")
    public List<Issue> getAllIssues(){
         return  this.issueService.getAllIssues();
    }

}

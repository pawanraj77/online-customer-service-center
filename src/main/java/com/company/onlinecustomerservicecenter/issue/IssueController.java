package com.company.onlinecustomerservicecenter.issue;

import com.company.onlinecustomerservicecenter.solution.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController {
    @Autowired
    private IssueService issueService;

    @PostMapping("issue")
    public Issue createIssue(@RequestBody Issue issue) throws IssueException {
        return this.issueService.createIssue(issue);
    }

    @PutMapping("issue/issueid/issuestatus/issuedescription/issuesolution/{issueId}/{issueStatus}/{issueDescription}/{issueSolution}")
    public Issue updateIssue(@PathVariable("issueId") Integer issueId, @PathVariable("issueStatus") String issueStatus, @PathVariable("issueDescription") String issueDescription, @PathVariable("issueSolution")Solution issueSolution)  throws IssueException{
        return this.issueService.updateIssue(issueId,issueStatus,issueDescription, issueSolution);
    }

    @PutMapping("issue/issueid/{issueId}")
    public Issue closeIssue(@PathVariable("issueId") Integer issueId)  throws IssueException{
        return this.issueService.closeIssue(issueId);
    }
    @PutMapping("issue/issueid/issuedescription/{issueId}/{issueDescription}")
    public Issue reOpenIssue(@PathVariable("issueId") Integer issueId, @PathVariable("issueDescription") String issueDescription)  throws IssueException{
        return this.issueService.reOpenIssue(issueId, issueDescription);
    }
    @DeleteMapping("issue/deleteissue/{issueId}")
    public Issue deleteIssue(@PathVariable("issueId") Integer issueId) throws IssueException{
        return this.issueService.deleteIssue(issueId);
    }
    @GetMapping("issue/issueid/{issueId}")
    public Issue viewIssue(@PathVariable("issueId") Integer issueId)  throws IssueException {
        return this.issueService.viewIssue(issueId);
    }
    @GetMapping("issue")
    public List<Issue> getAllIssues()  throws IssueException{
        return this.issueService.getAllIssues();
    }
    
    //     private final IssueService issueService;
//      @Autowired
//     public IssueController(IssueService issueService) {
//         this.issueService = issueService;
//     }

//     @PostMapping("add")
//     public Issue addIssue(Issue issue){
//         return this.issueService.addIssue(issue);
//     }

}






package com.company.onlinecustomerservicecenter.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SolutionController {
    //@Autowired
    private final SolutionService solutionService;
    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("add/{issueId}")
    public Solution postSolutionForGivenIssueId(@PathVariable("issueId") Integer issueId,Solution solution) throws SolutionException {
        return this.solutionService.postSolutionForGivenIssueId(issueId,solution);
    }
    @PutMapping("update/{issueId}")
    public Solution updateSolutionForGivenIssueId(@PathVariable("issueId") Integer issueId,Solution solution) throws SolutionException {
        return this.solutionService.updateSolutionForGivenIssueId(issueId,solution);
    }
    @GetMapping("all")
    public List<Solution> getAllSolution() throws SolutionException {
        return  this.solutionService.getAllSolution();
    }
    @GetMapping("search/{date}")
    public List<Solution>  searchSolutionsByDate(@PathVariable("date") LocalDate date) throws SolutionException {
         return this.solutionService.searchSolutionsByDate(date);
    }
    @GetMapping("check/solution/exists/{solutionId}")
    public Solution checkSolutionExists(@PathVariable Integer solutionId) throws SolutionException {
        return this.solutionService.checkSolutionExists(solutionId);
    }
}

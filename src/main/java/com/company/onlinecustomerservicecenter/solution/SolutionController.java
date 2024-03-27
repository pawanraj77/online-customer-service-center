package com.company.onlinecustomerservicecenter.solution;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("http://localhost:4800/")
@RestController
public class SolutionController {
    //@Autowired
    private final SolutionService solutionService;
    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("add/{issueId}")
    public Solution postSolutionForGivenIssueId( @PathVariable("issueId") Integer issueId, @Valid @RequestBody Solution solution) throws SolutionException {
        return this.solutionService.postSolutionForGivenIssueId(issueId,solution);
    }
    @PutMapping("update/{issueId}")
    public Solution updateSolutionForGivenIssueId( @PathVariable("issueId") Integer issueId,@Valid @RequestBody Solution solution) throws SolutionException {
        return this.solutionService.updateSolutionForGivenIssueId(issueId,solution);
    }
    @GetMapping("solutions")
    public List<Solution> getAllSolution() throws SolutionException {
        return  this.solutionService.getAllSolution();
    }
    @GetMapping("search/{date}")
    public List<Solution>  searchSolutionsByDate(@Valid  @PathVariable("date") LocalDate date) throws SolutionException {
         return this.solutionService.searchSolutionsByDate(date);
    }


}

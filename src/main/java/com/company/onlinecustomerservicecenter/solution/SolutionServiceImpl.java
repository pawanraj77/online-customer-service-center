package com.company.onlinecustomerservicecenter.solution;

import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
    private final IssueRepository issueRepository;
    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, IssueRepository issueRepository) {
        this.solutionRepository = solutionRepository;
        this.issueRepository = issueRepository;
    }




    @Override
    public Solution postSolutionForGivenIssueId(Integer issueId, Solution solution) throws SolutionException {
        Optional<Issue> issueOptional = this.issueRepository.findById(issueId);
        if (issueOptional.isPresent()) {
            Issue issue = issueOptional.get();
            // Ensure that the issue does not already have a solution
            if (issue.getSolution() == null) {
                solution.setIssue(issue);
                issue.setSolution(solution); // Set the solution for the issue
                this.solutionRepository.save(solution);
                this.issueRepository.save(issue);
                return solution;
            } else {
                // Handle the case when the issue already has a solution
                throw new SolutionException("Issue Already has a solution");
            }
        } else {
            // Handle the case when the issue with the given ID is not found
            throw new SolutionException("No issue exists with given id");
        }
    }

    @Override
    public Solution updateSolutionForGivenIssueId(Integer issueId, Solution solution) throws SolutionException {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        if (issueOptional.isPresent()) {
            Issue issue = issueOptional.get();
            if (issue.getSolution() != null) {
                Solution existingSolution = issue.getSolution();
                existingSolution.setDescription(solution.getDescription());
                existingSolution.setDate(solution.getDate());
                return solutionRepository.save(existingSolution);
            } else {
                // Handle the case when the issue does not have a solution
                throw new SolutionException("Issue does not have solution so it cannot be updated");
            }
        } else {
            // Handle the case when the issue with the given ID is not found
            throw new SolutionException("There is no issue with given id so it cannot be updated");
        }
    }

    @Override
    public List<Solution> getAllSolution() throws SolutionException {
        try {
            return solutionRepository.findAll();
        } catch(Exception e){
            throw new SolutionException("Failed to retrieve the solutions");
        }
    }

    @Override
    public List<Solution> searchSolutionsByDate(LocalDate date) throws SolutionException {
        try {
            return solutionRepository.findByDate(date);
        }catch(Exception e) {
            throw new SolutionException("Failed to search solutions by date");
        }

    }

    @Override
    public Boolean markSolutionAsResolved(Integer solutionId) {
        Optional<Solution> solutionOptional = solutionRepository.findById(solutionId);
        if (solutionOptional.isPresent()) {
            Solution solution = solutionOptional.get();
            solution.setResolved(true);
            solutionRepository.save(solution);
            return true;
        } else {
            return false; // Solution not found
        }
    }
}



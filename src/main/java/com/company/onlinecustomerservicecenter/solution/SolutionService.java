package com.company.onlinecustomerservicecenter.solution;

import java.time.LocalDate;
import java.util.List;

public interface SolutionService {

    Solution postSolutionForGivenIssueId(Integer issueId,Solution solution) throws SolutionException;

    Solution updateSolutionForGivenIssueId(Integer issueId, Solution solution) throws SolutionException;

    List<Solution> getAllSolution() throws SolutionException;

    List<Solution> searchSolutionsByDate(LocalDate date) throws SolutionException;

    Boolean markSolutionAsResolved(Integer solutionId);
}

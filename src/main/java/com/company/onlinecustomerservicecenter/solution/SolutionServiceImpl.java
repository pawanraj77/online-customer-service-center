package com.company.onlinecustomerservicecenter.solution;

import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*******************************************************************************************************************
 *          @author          Darshita Kochar
 *          Description      It is a service class that provides the services for posting solution for given issue,
                             updating solution for given issue, getting all solutions and searching solutions by date.
 *         Version           2023.3.3
 *         Created Date      21-FEB-2024
 ********************************************************************************************************************/

@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
    private final IssueRepository issueRepository;
    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, IssueRepository issueRepository) {
        this.solutionRepository = solutionRepository;
        this.issueRepository = issueRepository;
    }



    /************************************************************************************
     * Method:                   -postSolutionForGivenIssueId
     *Description:               -To post solution for a given issue
     * @param issueId            -Id of the issue whose solution need to be posted
     * @param solution           -Solution which need to be posted for the given issue
     * @return Solution          -Solution, if issue exist for which solution need to be posted
     * @throws SolutionException -It is raised when the solution cannot be posted , updated or
     *                            cannot be searched or retrieved.

     *Created By                  -Darshita Kochar
     *Created Date                -21-FEB-2024

     ************************************************************************************/

    @Override
    public Solution postSolutionForGivenIssueId(Integer issueId, Solution solution) throws SolutionException {
        Optional<Issue> issueOptional = this.issueRepository.findById(issueId);
        if (issueOptional.isPresent()) {
            Issue issue = issueOptional.get();
            // Ensure that the issue does not already have a solution
            if (issue.getSolution() == null) {
                solution.setIssue(issue);
                solution = this.solutionRepository.save(solution);

                issue.setSolution(solution); // Set the solution for the issue

                this.issueRepository.save(issue);
                return solution;
            } else {
                // Handle the case when the issue already has a solution
                throw new SolutionException("Issue already has a solution");
            }
        } else {
            // Handle the case when the issue with the given ID is not found
            throw new SolutionException("No issue exists with given id");
        }
    }

    /************************************************************************************
     * Method:                   -updateSolutionForGivenIssueId
     *Description:               -To update solution for a given issue
     * @param issueId            -Id of the issue whose solution need to be updated
     * @param solution           -Solution which need to be updated for the given issue
     * @return Solution          -Solution, if a solution exist for issue and need to be updated
     * @throws SolutionException -It is raised when the solution cannot be posted,updated or
     *                            cannot be searched or retrieved.

     *Created By                  -Darshita Kochar
     *Created Date                -21-FEB-2024

     ************************************************************************************/

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

    /************************************************************************************
     * Method:                   -getAllSolution
     *Description:               -To get all solutions present
     * @return List<Solution>    -List<Solution>, if solutions exist
     * @throws SolutionException -It is raised when the solution cannot be posted, updated or
     *                            cannot be searched or retrieved.

     *Created By                  -Darshita Kochar
     *Created Date                -21-FEB-2024

     ************************************************************************************/

    @Override
    public List<Solution> getAllSolution() throws SolutionException {
        try {
            return solutionRepository.findAll();
        } catch(Exception e){
            throw new SolutionException("Failed to retrieve the solutions");
        }
    }
    /************************************************************************************
     * Method:                   -searchSolutionsByDate
     *Description:               -To search solutions by date
     * @param date               -Date by which solutions need to be searched
     * @return List<Solution>    -List<Solution>, if a solutions exist for given date
     * @throws SolutionException -It is raised when the solution cannot be posted,updated or
     *                            cannot be searched or retrieved

     *Created By                  -Darshita Kochar
     *Created Date                -22-FEB-2024

     ************************************************************************************/
    @Override
    public List<Solution> searchSolutionsByDate(LocalDate date) throws SolutionException {
        try {
            return solutionRepository.findByDate(date);
        }catch(Exception e) {
            throw new SolutionException("Failed to search solutions by date");
        }

    }


}



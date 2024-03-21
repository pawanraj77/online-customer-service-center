package com.company.onlinecustomerservicecenter.solution;

import com.company.onlinecustomerservicecenter.issue.Issue;
import com.company.onlinecustomerservicecenter.issue.IssueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;



@SpringBootTest
 class SolutionServiceTest {

    @Autowired
    private SolutionService solutionService;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private IssueRepository issueRepository;

    //+ve
    @Test
    void postSolutionWithValidIssueId() throws SolutionException {

        Issue issue = new Issue(1, "Software", "Black Screen", null, null);
        issueRepository.save(issue);
        Solution solution = new Solution(1, "Restart your Computer", LocalDate.now(), issue);

        Solution postedSolution = solutionService.postSolutionForGivenIssueId(1, solution);

        Assertions.assertNotNull(postedSolution);

    }
    //-ve
    @Test
    void postSolutionWithInvalidIssueId() {

        Issue issue = new Issue(1, "Software", "Black Screen", null, null);
        issueRepository.save(issue);
        Solution solution = new Solution(1, "Restart your Computer", LocalDate.now(),  issue);

        Assertions.assertThrows(SolutionException.class, () -> solutionService.postSolutionForGivenIssueId(99, solution));
    }
    //-ve
    @Test
    void postSolutionWithNullSolution() {
        Assertions.assertThrows(SolutionException.class, () -> solutionService.postSolutionForGivenIssueId(1, null));
    }
    //+ve
    @Test
    void updateSolutionWithValidIssueId() throws SolutionException {
        Issue issue = new Issue(1, "Software", "Black Screen", null, null);
        issueRepository.save(issue);

        Solution initialSolution = new Solution(1, "Restart your Computer", LocalDate.now(),  issue);
        solutionRepository.save(initialSolution);

        issue.setSolution(initialSolution);
        issueRepository.save(issue);

        Solution updatedSolution = new Solution(1, "Scan for malware", LocalDate.now(), issue);
        Solution updatedSolutionResult = solutionService.updateSolutionForGivenIssueId(1, updatedSolution);

        Assertions.assertNotNull(updatedSolutionResult);
        Assertions.assertEquals("Scan for malware", updatedSolutionResult.getDescription());
    }
    //-ve
    @Test
    void updateSolutionWithInvalidIssueId() {
        Solution updatedSolution = new Solution(2, "Scan for malware", LocalDate.now(),  null);
       Assertions.assertThrows(SolutionException.class, () -> solutionService.updateSolutionForGivenIssueId(100, updatedSolution));

    }
    //-ve
    @Test
    void updateSolutionForIssueWithoutExistingSolution()  {

        Issue issue = new Issue(1, "Software", "Black Screen", null, null);
        issueRepository.save(issue);

        Solution updatedSolution = new Solution(2, "Scan for malware", LocalDate.now(),  issue);

        try {
            solutionService.updateSolutionForGivenIssueId(1, updatedSolution);
        } catch (SolutionException e) {
            Assertions.assertEquals("Issue does not have solution so it cannot be updated", e.getMessage());
        }

    }
    //+ve
   @Test
    void getAllSolutionWithData() throws SolutionException {
        Solution s1 =  new Solution(1, "Restart your Computer", LocalDate.now(), null);
        Solution s2 =  new Solution(2, "Scan for Malware", LocalDate.now(), null);
        solutionRepository.save(s1);
        solutionRepository.save(s2);
        List<Solution> solutionList = this.solutionService.getAllSolution();
        Assertions.assertNotNull(solutionList);
   }
   //-ve
   @Test
    void getAllSolutionWithoutData(){
       try {
           List<Solution> solutionList = this.solutionService.getAllSolution();
       } catch (SolutionException e) {
         Assertions.assertEquals("No Solutions exists",e.getMessage());
       }
   }

   //+ve
@Test
   void searchSolutionsByDateWithData() throws SolutionException {
        LocalDate searchDate = LocalDate.of(2022, 3, 15);
       Solution s1 =  new Solution(1, "Restart your Computer", searchDate, null);
       Solution s2 =  new Solution(2, "Scan for Malware", searchDate, null);
       List<Solution> solutionList = this.solutionService.searchSolutionsByDate(searchDate);
       Assertions.assertNotNull(solutionList);
   }
   //-ve
   @Test
    void searchSolutionsByDateWithNoData(){
       LocalDate searchDate = LocalDate.of(2022, 3, 15);
       try {
           List<Solution> solutionsByDate = solutionService.searchSolutionsByDate(searchDate);
       } catch (SolutionException e) {
           Assertions.assertEquals("No Solutions exists",e.getMessage());
       }
   }

}





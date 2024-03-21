package com.company.onlinecustomerservicecenter.issue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IssueServiceTest {
    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueRepository issueRepository;

    @Test
    @DisplayName("Create New Issue Successfully")
    void createNewIssueTest() throws IssueException{
        Issue newIssue = new Issue(1, "Network", "Internet connection problem", null, null, null);

        try {
            Issue savedIssue = this.issueService.createIssue(newIssue);
            Assertions.assertNotNull(savedIssue);
            Assertions.assertEquals(newIssue.getDescription(), savedIssue.getDescription());

            // Cleanup
            issueRepository.delete(savedIssue);
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Update Existing Issue")
    @Transactional
    void updateIssueTest() throws IssueException{
        // Prepare an issue and save it
        Issue issue = new Issue(2, "Software", "Application crash", null, null, null);


        // Now update this issue using the service
        try {
            Issue savedIssue = this.issueService.createIssue(issue);
            Issue updatedIssue = this.issueService.updateIssue(savedIssue.getIssueId(), "Updated Description", null);

            Assertions.assertNotNull(updatedIssue);
            Assertions.assertTrue(updatedIssue.getDescription().contains("Updated Description"));

            // Cleanup is not necessary due to @Transactional, which will roll back changes
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Delete Issue Successfully")
    void deleteIssueTest() throws IssueException {
        // Prepare an issue and save it
        Issue issue = new Issue(3, "Hardware", "Keyboard issue", null, null, null);

        // Delete this issue using the service
        try {
            Issue savedIssue = this.issueService.createIssue(issue);
            Issue deletedIssue = issueService.deleteIssue(savedIssue.getIssueId());

            Optional<Issue> findDeletedIssue = issueRepository.findByIssueId(savedIssue.getIssueId());

            Assertions.assertFalse(findDeletedIssue.isPresent());
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Close an Issue")
    void closeIssueTest() throws IssueException{
        // Prepare an issue and save it
        Issue issue = new Issue(4, "Software", "Software update required", null, null, null);

        // Close this issue
        try {
            Issue savedIssue = this.issueService.createIssue(issue);
            Issue closedIssue = this.issueService.closeIssue(savedIssue.getIssueId());

            Assertions.assertEquals("Closed", closedIssue.getIssueType());

            // Cleanup
            issueRepository.delete(closedIssue);
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Re-Open an Issue")
    void reopenIssueTest() throws IssueException{
        // Prepare an issue, assume it is closed, and save it
        Issue issue = new Issue(5, "Network", "Router reset needed", null, null, null);

        // Re-open this issue
        try {
            Issue savedIssue = this.issueService.createIssue(issue);
            Issue reopenedIssue = this.issueService.reOpenIssue(savedIssue.getIssueId(), "Additional details");

            Assertions.assertEquals("Re-Opened", reopenedIssue.getIssueType());
            assertTrue(reopenedIssue.getDescription().contains("Additional details"));

            // Cleanup
            issueRepository.delete(reopenedIssue);
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("View Specific Issue")
    void viewIssueTest() throws IssueException{
        // Prepare an issue and save it
        Issue issue = new Issue(6, "Account", "Password reset", null, null, null);
        try {
            Issue savedIssue = this.issueService.createIssue(issue);
            Issue foundIssue = this.issueService.viewIssue(savedIssue.getIssueId());

            Assertions.assertNotNull(foundIssue);
            Assertions.assertEquals(issue.getIssueId(), foundIssue.getIssueId());
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Get All Issues")
    void getAllIssuesTest() throws IssueException {
        // Prepare some issues and save them
        Issue issue1 = new Issue(7, "Account", "Password forgot", null, null, null); // Setup issue1
        Issue issue2 = new Issue(8, "Account", "Password retrieve", null, null, null); // Setup issue2


        try {
            Issue savedIssue1 = this.issueService.createIssue(issue1);
            Issue savedIssue2 = this.issueService.createIssue(issue2);
            List<Issue> allIssues = this.issueService.getAllIssues();

            Assertions.assertNotNull(allIssues);
            Assertions.assertEquals(2, allIssues.size());
        } catch (IssueException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

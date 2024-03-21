package com.company.onlinecustomerservicecenter.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;
    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue addIssue(Issue issue) {
        return this.issueRepository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return this.issueRepository.findAll();
    }
}

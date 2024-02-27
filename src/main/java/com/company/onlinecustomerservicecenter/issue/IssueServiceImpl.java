package com.company.onlinecustomerservicecenter.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }
  
  private final IssueRepository issueRepository;

//     @Override
//     public Issue addIssue(Issue issue) {
//         return this.issueRepository.save(issue);
//     }

}

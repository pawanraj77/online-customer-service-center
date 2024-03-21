package com.company.onlinecustomerservicecenter.solution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution,Integer> {
    List<Solution> findByDate(LocalDate date);
}

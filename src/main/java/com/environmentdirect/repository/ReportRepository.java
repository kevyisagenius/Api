package com.environmentdirect.repository;

import com.environmentdirect.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Basic CRUD operations are inherited from JpaRepository
    // You can add custom query methods here if needed, e.g.:
    // List<Report> findByStatus(String status);
    // List<Report> findByIssueType(String issueType);
} 
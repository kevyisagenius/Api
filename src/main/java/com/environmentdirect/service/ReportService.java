package com.environmentdirect.service;

import com.environmentdirect.model.Report;
import com.environmentdirect.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
// import java.nio.file.Path; // For file handling later
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final FileStorageService fileStorageService; // Inject FileStorageService

    @Autowired
    public ReportService(ReportRepository reportRepository, FileStorageService fileStorageService) { // Add FileStorageService
        this.reportRepository = reportRepository;
        this.fileStorageService = fileStorageService;
    }

    public Report createReport(Report report, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = fileStorageService.store(imageFile);
                // For now, store just the filename. We'll construct full URLs when serving.
                report.setImageUrl(filename); 
            } catch (Exception e) {
                // Handle file storage exception, e.g., log it or throw a custom exception
                // For simplicity, we're not throwing a fatal error here, report can be saved without image
                System.err.println("Could not store image file: " + e.getMessage());
                report.setImageUrl(null); // Ensure imageUrl is null if storage failed
            }
        }
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    // We can add methods to update status, etc.
    // public Optional<Report> updateReportStatus(Long id, String newStatus) {
    //     Optional<Report> reportOptional = reportRepository.findById(id);
    //     if (reportOptional.isPresent()) {
    //         Report report = reportOptional.get();
    //         report.setStatus(newStatus);
    //         return Optional.of(reportRepository.save(report));
    //     }
    //     return Optional.empty();
    // }

    // Add other service methods as needed (e.g., findByStatus, findByLocation, etc.)
} 
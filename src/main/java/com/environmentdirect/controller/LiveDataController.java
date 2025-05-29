package com.environmentdirect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for providing live environmental data.
 * This endpoint serves data for the LiveDataDashboardSection component in the frontend.
 */
@RestController
@RequestMapping("/api/live-data")
public class LiveDataController {

    /**
     * Get current environmental metrics data.
     * @return A map containing metrics data for air quality, water quality, and temperature.
     */
    @GetMapping
    public ResponseEntity<Map<String, Map<String, String>>> getLiveData() {
        // In a real application, this data would come from sensors, external APIs, or a database
        Map<String, Map<String, String>> metricsData = new HashMap<>();
        
        // Air Quality Index data
        Map<String, String> aqiData = new HashMap<>();
        aqiData.put("value", "42");
        aqiData.put("unit", "Good");
        aqiData.put("details", "Roseau Capital Average");
        metricsData.put("aqi", aqiData);
        
        // Water Quality data
        Map<String, String> waterQualityData = new HashMap<>();
        waterQualityData.put("value", "87");
        waterQualityData.put("unit", "Excellent");
        waterQualityData.put("details", "Layou River Monitoring Point");
        metricsData.put("waterQuality", waterQualityData);
        
        // Temperature data
        Map<String, String> temperatureData = new HashMap<>();
        temperatureData.put("value", "27.5");
        temperatureData.put("unit", "°C");
        temperatureData.put("details", "Canefield Airport Vicinity");
        metricsData.put("temperature", temperatureData);
        
        return ResponseEntity.ok(metricsData);
    }
}
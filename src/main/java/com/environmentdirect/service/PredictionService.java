package com.environmentdirect.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.util.Arrays;

/**
 * Service for generating prediction data for various environmental forecasts.
 * This service provides methods for eco-tourism hotspot pressure and flood risk forecasting.
 */
@Service
public class PredictionService {

    /**
     * Get eco-tourism hotspot pressure forecast for a specific site.
     * 
     * @param siteId The identifier for the tourism site.
     * @return A map containing the forecast data.
     */
    public Map<String, Object> getEcoTourismPressure(String siteId) {
        Map<String, Object> forecast = new HashMap<>();
        
        // Default values
        String siteName = "Unknown Site";
        String expectedVisitorLoad = "Moderate";
        double confidenceScore = 0.7;
        List<String> contributingFactors = new ArrayList<>();
        String recommendation = "No specific recommendations at this time.";
        
        // Set site-specific data based on siteId
        switch (siteId) {
            case "boiling-lake":
                siteName = "Boiling Lake Trail";
                expectedVisitorLoad = "High";
                confidenceScore = 0.85;
                contributingFactors = Arrays.asList("Weekend", "Cruise ship in port");
                recommendation = "Visit before 10 AM or after 3 PM for a less crowded experience.";
                break;
            case "trafalgar-falls":
                siteName = "Trafalgar Falls";
                expectedVisitorLoad = "High";
                confidenceScore = 0.9;
                contributingFactors = Arrays.asList("Weekend", "Holiday season");
                recommendation = "Consider visiting on weekdays for a more peaceful experience.";
                break;
            case "middleham-falls":
                siteName = "Middleham Falls";
                expectedVisitorLoad = "Low";
                confidenceScore = 0.75;
                contributingFactors = Arrays.asList("Weekday", "Off-season");
                recommendation = "Great time to visit with minimal crowds.";
                break;
            case "emerald-pool":
                siteName = "Emerald Pool";
                expectedVisitorLoad = "Moderate";
                confidenceScore = 0.8;
                contributingFactors = Arrays.asList("Weekday", "Cruise ship in port");
                recommendation = "Visit early morning for the best experience.";
                break;
            default:
                // Use default values for unknown sites
                break;
        }
        
        // Populate the forecast map
        forecast.put("siteId", siteId);
        forecast.put("siteName", siteName);
        forecast.put("predictionDate", Instant.now().toString());
        forecast.put("expectedVisitorLoad", expectedVisitorLoad);
        forecast.put("confidenceScore", confidenceScore);
        forecast.put("contributingFactors", contributingFactors);
        forecast.put("recommendation", recommendation);
        
        return forecast;
    }
    
    /**
     * Get flood risk forecast for a specific region.
     * 
     * @param region The identifier for the region.
     * @return A map containing the forecast data.
     */
    public Map<String, Object> getFloodRisk(String region) {
        Map<String, Object> forecast = new HashMap<>();
        
        // Default values
        String regionName = "Unknown Region";
        String floodRiskLevel = "Low";
        double confidenceScore = 0.7;
        String details = "No specific details available.";
        List<String> affectedAreas = new ArrayList<>();
        
        // Set region-specific data based on region
        switch (region) {
            case "Portsmouth":
                regionName = "Portsmouth";
                floodRiskLevel = "Moderate";
                confidenceScore = 0.78;
                details = "River levels are elevated. Minor localized flooding possible in coastal areas if heavy showers persist.";
                affectedAreas = Arrays.asList("Lower Reach Area", "Near Purple Turtle Beach");
                break;
            case "RoseauSouth":
                regionName = "Roseau South";
                floodRiskLevel = "High";
                confidenceScore = 0.85;
                details = "Recent heavy rainfall has saturated soils. High risk of flash flooding in low-lying areas.";
                affectedAreas = Arrays.asList("Newtown", "Loubiere", "Pointe Michel");
                break;
            case "LayouValley":
                regionName = "Layou Valley";
                floodRiskLevel = "Low";
                confidenceScore = 0.9;
                details = "River levels normal. No significant rainfall expected in the next 48 hours.";
                affectedAreas = Arrays.asList();
                break;
            case "MarigotArea":
                regionName = "Marigot Area";
                floodRiskLevel = "Moderate";
                confidenceScore = 0.75;
                details = "Moderate rainfall expected. Some localized flooding possible in known problem areas.";
                affectedAreas = Arrays.asList("Coastal Road", "River Crossings");
                break;
            default:
                // Use default values for unknown regions
                break;
        }
        
        // Populate the forecast map
        forecast.put("regionName", regionName);
        forecast.put("predictionDate", Instant.now().toString());
        forecast.put("floodRiskLevel", floodRiskLevel);
        forecast.put("confidenceScore", confidenceScore);
        forecast.put("details", details);
        forecast.put("affectedAreas", affectedAreas);
        
        return forecast;
    }
    
    /**
     * Get all eco-tourism hotspot pressure forecasts.
     * 
     * @return A list of forecasts for all known sites.
     */
    public List<Map<String, Object>> getAllEcoTourismPressures() {
        List<Map<String, Object>> forecasts = new ArrayList<>();
        
        // Add forecasts for all known sites
        forecasts.add(getEcoTourismPressure("boiling-lake"));
        forecasts.add(getEcoTourismPressure("trafalgar-falls"));
        forecasts.add(getEcoTourismPressure("middleham-falls"));
        forecasts.add(getEcoTourismPressure("emerald-pool"));
        
        return forecasts;
    }
    
    /**
     * Get all flood risk forecasts.
     * 
     * @return A list of forecasts for all known regions.
     */
    public List<Map<String, Object>> getAllFloodRisks() {
        List<Map<String, Object>> forecasts = new ArrayList<>();
        
        // Add forecasts for all known regions
        forecasts.add(getFloodRisk("Portsmouth"));
        forecasts.add(getFloodRisk("RoseauSouth"));
        forecasts.add(getFloodRisk("LayouValley"));
        forecasts.add(getFloodRisk("MarigotArea"));
        
        return forecasts;
    }
}
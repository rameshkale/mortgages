package com.mortgages.bslayer;

import com.mortgages.bslayer.entity.MortgageApplication;
import com.mortgages.bslayer.request.NewApplicationsRequest;
import com.mortgages.bslayer.response.MortgageApplicationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
public class RestServiceController {

    @Autowired
    private MortgageService dataLayerService;

    @PostMapping(path="/mortgages/applications",
            consumes = {"application/json"},
            produces = {"application/json"})
    public String insertApplication(@RequestBody NewApplicationsRequest newApplicationsRequest) {
        String response = dataLayerService.insertApplication(newApplicationsRequest);
        return "{message:\"success\"}";
    }

    @GetMapping(path="/mortgages/applications",
            produces = "application/json")
    public MortgageApplicationsResponse getApplications(@RequestParam String sort) {
        MortgageApplicationsResponse response = dataLayerService.getApplications(sort);
        return response;
    }
}
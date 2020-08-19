package com.mortgages.bslayer;

import com.mortgages.bslayer.entity.MortgageApplication;
import com.mortgages.bslayer.request.NewApplicationsRequest;
import com.mortgages.bslayer.response.MortgageApplicationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rameshkale on 16/08/20.
 */
@Service
public class MortgageService {

    @Autowired
    private Environment environment;

    @Autowired
    RestTemplate restTemplate;

    public String insertApplication(NewApplicationsRequest newApplication) {

        String endpoint = environment.getProperty("endpoint.ds-service");
        HttpEntity<NewApplicationsRequest> request = new HttpEntity<>(newApplication);
        return restTemplate.postForObject(endpoint, request, String.class);
    }

    public MortgageApplicationsResponse getApplications(String sort) {
        String endpoint = environment.getProperty("endpoint.ds-service");
        ResponseEntity<MortgageApplicationsResponse> response =
                restTemplate.getForEntity(endpoint+"?sort="+sort, MortgageApplicationsResponse.class);
        return response.getBody();
    }

}

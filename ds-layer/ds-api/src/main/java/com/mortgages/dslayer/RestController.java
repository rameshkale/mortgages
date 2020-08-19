package com.mortgages.dslayer;

import com.mortgages.dslayer.engine.ProcessEngine;
import com.mortgages.dslayer.engine.StoreSorter;
import com.mortgages.dslayer.engine.entity.Application;
import com.mortgages.dslayer.engine.impl.CreatedDateAscSorter;
import com.mortgages.dslayer.engine.impl.CreatedDateDescSorter;
import com.mortgages.dslayer.engine.impl.OfferDateAscSorter;
import com.mortgages.dslayer.engine.impl.OfferDateDescSorter;
import com.mortgages.dslayer.exceptions.BusinessException;
import com.mortgages.dslayer.http.entity.MortgageApplication;
import com.mortgages.dslayer.http.request.NewApplicationsRequest;
import com.mortgages.dslayer.http.response.MortgageApplicationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    ProcessEngine processEngine;

    @GetMapping(path="/mortgages/applications", produces = "application/json")
    public MortgageApplicationsResponse
    getApplications(@RequestParam String sort) throws ParseException {
        System.out.println("BootRestController.getApplications");

        if(sort==null || "".equals(sort)){sort = "Default";}

        ProcessEngine.SortedBy sortedBy = ProcessEngine.SortedBy.valueOf(sort);

        Application[] applications = processEngine.getApplications(sortedBy);

        MortgageApplicationsResponse response = new MortgageApplicationsResponse();
        MortgageApplication mortgageApplications[] = copyApplicationFromStoreObject(applications);
        response.setData(mortgageApplications);

        return response;
    }

    @PostMapping(path="/mortgages/applications",
            consumes = {"application/json"}, produces = {"application/json"})
    public String
        insertApplication(@RequestBody NewApplicationsRequest newAppRequest) {
        System.out.println("BootRestController.insertApplication");

        MortgageApplication newApplication = newAppRequest.getData();
        Application application = copyApplicationInStoreObject(newApplication);
        processEngine.validateAppication(application);
        processEngine.insertApplication(application);

        processEngine.displayStore();

        return "{\"message\":\"success\"}";
    }

    private Application copyApplicationInStoreObject(MortgageApplication newApplication) {
        Application application =
                new Application(newApplication.getId(),newApplication.getVersion());

        application.setPrdId(newApplication.getProductId());
        application.setOffrId(newApplication.getOfferId());
        application.setOffrDt(newApplication.getOfferDate());
        application.setCreDt(newApplication.getCreatedDate());
        application.setExpired(newApplication.getExpired());

        return application;
    }

    private MortgageApplication[] copyApplicationFromStoreObject(Application[] storeObjects) {

        MortgageApplication mortgageApplications[] = new MortgageApplication[storeObjects.length];

        for (int i=0; i < storeObjects.length; i++ ) {
            Application element = storeObjects[i];
            MortgageApplication temp = new MortgageApplication();
            temp.setId(element.getId());
            temp.setVersion(element.getVer());
            temp.setOfferId(element.getOffrId());
            temp.setProductId(element.getPrdId());
            temp.setCreatedDate(element.getCreDt());
            temp.setOfferDate(element.getOffrDt());
            temp.setExpired(element.isExpired());
            mortgageApplications[i] = temp;
        }
        return mortgageApplications;
    }

}
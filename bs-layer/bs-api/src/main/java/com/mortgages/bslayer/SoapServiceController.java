package com.mortgages.bslayer;

import com.mortgages.bslayer.entity.MortgageApplication;
import com.mortgages.bslayer.request.NewApplicationsRequest;
import com.mortgages.bslayer.response.MortgageApplicationsResponse;
import io.spring.guides.gs_producing_web_service.GetSoapApplicationsResponse;
import io.spring.guides.gs_producing_web_service.SoapApplicationsRequest;
import io.spring.guides.gs_producing_web_service.SoapApplicationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Endpoint
public class SoapServiceController {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private MortgageService dataLayerService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "soapApplicationsRequest")
    @ResponsePayload
    public SoapApplicationsResponse newApplicationRequest(@RequestPayload SoapApplicationsRequest soapRequest) {
        SoapApplicationsResponse response = new SoapApplicationsResponse();
        NewApplicationsRequest newApplicationsRequest = copySoapEntityToRestEntity(soapRequest);
        String result = dataLayerService.insertApplication(newApplicationsRequest);
        return response;
    }

    // Soap Service TODO
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSoapApplicationsResponse")
    @ResponsePayload
    public GetSoapApplicationsResponse getSoapApplicationsResponse() {
        GetSoapApplicationsResponse soapResponse = new GetSoapApplicationsResponse();
        MortgageApplicationsResponse response = dataLayerService.getApplications("");
        return null;
    }

    private NewApplicationsRequest copySoapEntityToRestEntity(SoapApplicationsRequest soapRequest) {
        NewApplicationsRequest newApplicationsRequest = new NewApplicationsRequest();

        MortgageApplication app =  new MortgageApplication();
        app.setId(soapRequest.getId());
        app.setVersion(soapRequest.getVersion());
        app.setOfferId(soapRequest.getOfferId());
        app.setProductId(soapRequest.getProductId());
        app.setOfferDate(soapRequest.getOfferDate());
        app.setCreatedDate(soapRequest.getCreatedDate());
        app.setExpired(soapRequest.isExpired());

        newApplicationsRequest.setData(app);
        return newApplicationsRequest;
    }

}
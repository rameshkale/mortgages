package com.mortgages.bslayer.request;

import com.mortgages.bslayer.entity.MortgageApplication;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 19/08/20.
 */
public class NewApplicationsRequestTest {
    @Test
    public void TestNewApplicationsRequest() throws Exception {
        NewApplicationsRequest nr = new NewApplicationsRequest();
        MortgageApplication app = new MortgageApplication();
        app.setId("M1");
        nr.setData(app);
        assertTrue(nr.getData().getId().equals("M1"));
    }

}
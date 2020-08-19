package com.mortgages.dslayer.engine.impl;

import com.mortgages.dslayer.engine.ProcessEngine;
import com.mortgages.dslayer.engine.entity.Application;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 18/08/20.
 */
public class MortgageOriginationEngineTest {
    BasicApplicationStore bas;
    Application application;
    MortgageOriginationEngine moe;
    @Before
    public void setUp() throws Exception {
        bas = new BasicApplicationStore();

        application = new Application("M1",1);
        application.setOffrId("OFF1");
        application.setOffrDt("12/12/2020");
        application.setPrdId("PRD1");
        application.setExpired(true);
        application.setCreDt("12/11/2019");

        bas.add(application);

        moe = new MortgageOriginationEngine(bas);
    }

    @Test
    public void insertApplication() throws Exception {
        moe.insertApplication(application);
    }

    @Test
    public void getApplication() throws Exception {
        moe.getApplication("M1",1);
    }

    @Test
    public void getApplications() throws Exception {
        Application[] applications = moe.getApplications(ProcessEngine.SortedBy.CreatedDateAsc);
        assertTrue(applications.length==1);
    }

    @Test
    public void displayStore() throws Exception {
        moe.displayStore();
    }

}
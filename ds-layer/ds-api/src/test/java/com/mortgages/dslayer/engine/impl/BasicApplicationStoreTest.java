package com.mortgages.dslayer.engine.impl;

import com.mortgages.dslayer.engine.entity.Application;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 18/08/20.
 */
public class BasicApplicationStoreTest {

    private BasicApplicationStore bas;
    private Application application;

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
    }

    @Test
    public void add() throws Exception {
        assertTrue(bas.get(0).getId().equals("M1"));
        assertTrue(bas.get(0).getVer()==1);
    }

    @Test
    public void get() throws Exception {
        Application app = bas.get(0);
        assertTrue(app.getId().equals("M1"));
    }

    @Test
    public void getAll() throws Exception {
        Application[] all = bas.getAll();
        assertTrue(all.length==1);
    }

    @Test
    public void size() throws Exception {
        assertTrue(bas.size() == 1);
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(bas.isEmpty()==false);
    }

    @Test
    public void delete() throws Exception {
        String toString = bas.toString();

        BasicApplicationStore bas1 = new BasicApplicationStore(5);
        BasicApplicationStore bas2 = new BasicApplicationStore(0);

        bas1.delete(application);

    }


}
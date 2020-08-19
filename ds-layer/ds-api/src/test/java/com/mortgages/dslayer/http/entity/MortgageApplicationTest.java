package com.mortgages.dslayer.http.entity;

import com.mortgages.dslayer.engine.entity.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 18/08/20.
 */
public class MortgageApplicationTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        Application application = new Application("M1",1);
        application.setOffrId("OFF1");
        application.setOffrDt("12/12/2020");
        application.setPrdId("PRD1");
        application.setExpired(true);
        application.setCreDt("12/11/2019");

        Assert.assertEquals(application.getId(),"M1");
        Assert.assertEquals(application.getVer(),1);
        Assert.assertEquals(application.getOffrId(),"OFF1");
        Assert.assertEquals(application.getPrdId(),"PRD1");
        Assert.assertEquals(application.getOffrDt(),"12/12/2020");
        Assert.assertEquals(application.isExpired(),true);
        Assert.assertEquals(application.getCreDt(),"12/11/2019");

        String toString = application.toString();

        int hashCode = application.hashCode();

        Application application1 = new Application("M1",1);
        application1.setOffrId("OFF1");
        application1.setOffrDt("12/12/2020");
        application1.setPrdId("PRD1");
        application1.setExpired(true);
        application1.setCreDt("12/11/2019");

        assertTrue(application.equals(application1) && application1.equals(application));
        assertTrue(application.hashCode() == application1.hashCode());

        application1.setId("M2");
        application1.setVer(1);
        assertEquals(application1.getId(),"M2");
        assertEquals(application1.getVer(),1);
    }
}
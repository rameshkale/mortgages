package com.mortgages.bslayer.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 19/08/20.
 */
public class MortgageApplicationTest {
    @Test
    public void getId() throws Exception {
        MortgageApplication app = new MortgageApplication();
        app.setId("M1");
        app.setVersion(1);
        app.setOfferId("OFF1");
        app.setOfferDate("12/12/2020");
        app.setProductId("PRD1");
        app.setExpired(true);
        app.setCreatedDate("12/11/2019");


        assertTrue(app.getId().equals("M1"));
        assertTrue(app.getVersion().equals(1));
        assertTrue(app.getOfferId().equals("OFF1"));
        assertTrue(app.getOfferDate().equals("12/12/2020"));
        assertTrue(app.getProductId().equals("PRD1"));
        assertTrue(app.getExpired().equals(true));
        assertTrue(app.getCreatedDate().equals("12/11/2019"));
    }

}
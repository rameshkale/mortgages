package com.mortgages.bslayer.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 19/08/20.
 */
public class BusinessExceptionTest {
    @Test
    public void getCode() throws Exception {
        BusinessException e = new BusinessException();
        e.setCode("ERROR_101");
        e.setDescription("Description");
        assertTrue(e.getCode().equals("ERROR_101"));
        assertTrue(e.getDescription().equals("Description"));
    }

}
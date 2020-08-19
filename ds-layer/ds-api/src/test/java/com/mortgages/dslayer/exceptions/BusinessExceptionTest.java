package com.mortgages.dslayer.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 18/08/20.
 */
public class BusinessExceptionTest {
    @Test
    public void testBusinessException() throws Exception {
        BusinessException be =
                new BusinessException(BusinessException.BusinessError.ERROR_101,"Description");
        be.setCode(BusinessException.BusinessError.ERROR_101.toString());
        be.setDescription("Description");
        be.toString();
        assertTrue(be.getCode().equals(BusinessException.BusinessError.ERROR_101.toString()));
        assertTrue(be.getDescription().equals("Description"));
    }

}
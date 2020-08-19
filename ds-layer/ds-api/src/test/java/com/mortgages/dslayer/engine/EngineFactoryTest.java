package com.mortgages.dslayer.engine;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Created by rameshkale on 18/08/20.
 */
public class EngineFactoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getBasicEngine() throws Exception {
        ProcessEngine basicEngine = EngineFactory.getBasicEngine();
        assertTrue(basicEngine!=null);

    }

    @Test
    public void privateConstructorTest() throws Exception {
        Constructor<EngineFactory> constructor = EngineFactory.class.getDeclaredConstructor();
        assertEquals(constructor.isAccessible(), false);
        constructor.setAccessible(true);
        constructor.newInstance((Object[]) null);
    }



}
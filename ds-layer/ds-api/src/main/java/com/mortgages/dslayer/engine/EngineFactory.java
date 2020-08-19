package com.mortgages.dslayer.engine;

import com.mortgages.dslayer.engine.impl.BasicApplicationStore;
import com.mortgages.dslayer.engine.impl.MortgageOriginationEngine;

/**
 * Created by rameshkale on 14/08/20.
 */
public class EngineFactory {

    private static ProcessEngine engine = null;
    private EngineFactory(){

    }

    synchronized public static ProcessEngine getBasicEngine(){
        if(engine==null) {
            ApplicationStore store = new BasicApplicationStore();
            engine = new MortgageOriginationEngine(store);
        }
        return engine;
    }
}

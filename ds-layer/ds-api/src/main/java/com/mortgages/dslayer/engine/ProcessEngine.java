package com.mortgages.dslayer.engine;

import com.mortgages.dslayer.engine.entity.Application;
import com.mortgages.dslayer.exceptions.BusinessException;

/**
 * Created by rameshkale on 14/08/20.
 */

// Choose multiple primitive data structures and provider better performance for insertion and retrieval
// Sort using offer date and created data

public interface ProcessEngine {
    public enum SortedBy {
        OfferDateAsc,
        OfferDateDesc,
        CreatedDateAsc,
        CreatedDateDesc,
        Default
    }
    public boolean validateAppication(Application app);
    public boolean insertApplication(Application app);
    public boolean getApplication(String appId, int version);
    public Application[] getApplications(SortedBy field);
    public void displayStore();
}

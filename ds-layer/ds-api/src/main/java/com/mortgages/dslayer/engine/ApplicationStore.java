package com.mortgages.dslayer.engine;

import com.mortgages.dslayer.engine.entity.Application;

/**
 * Created by rameshkale on 14/08/20.
 */
public interface ApplicationStore {
    public boolean add(Application application);
    public Application replace(int index, Application application);
    public Application get(int index);
    public Application[] getAll();
    public int size();
    public boolean delete(Application application);
    public boolean isEmpty();
}

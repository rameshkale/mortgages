package com.mortgages.dslayer.engine;

import com.mortgages.dslayer.engine.entity.Application;

import java.text.ParseException;

/**
 * Created by rameshkale on 18/08/20.
 */
public interface StoreSorter {
    public Application[] sort(Application[] applications) throws ParseException;
}

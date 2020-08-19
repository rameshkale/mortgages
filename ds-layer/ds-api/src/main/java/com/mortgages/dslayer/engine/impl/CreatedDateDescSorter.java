package com.mortgages.dslayer.engine.impl;

import com.mortgages.dslayer.engine.StoreSorter;
import com.mortgages.dslayer.engine.entity.Application;

import java.text.ParseException;

/**
 * Created by rameshkale on 18/08/20.
 */
public class CreatedDateDescSorter implements StoreSorter
{

    @Override
    public Application[] sort(Application[] app) throws ParseException {
        for (int a = 1; a < app.length; a++) {
            for (int b = 0; b < app.length - a; b++) {
                if (((app[b].getCreatedDate())
                        .compareTo((app[b + 1].getCreatedDate()))) < 0) {
                    Application temp = app[b];
                    app[b] = app[b + 1];
                    app[b + 1] = temp;
                }
            }
        }
        return app;
    }
}

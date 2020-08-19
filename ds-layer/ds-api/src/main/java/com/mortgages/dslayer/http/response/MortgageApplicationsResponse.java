package com.mortgages.dslayer.http.response;

import com.mortgages.dslayer.http.entity.MortgageApplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rameshkale on 13/08/20.
 */
public class MortgageApplicationsResponse implements Serializable {
    private MortgageApplication[] data;

    public MortgageApplication[] getData() {
        return data;
    }

    public void setData(MortgageApplication[] data) {
        this.data = data;
    }
}

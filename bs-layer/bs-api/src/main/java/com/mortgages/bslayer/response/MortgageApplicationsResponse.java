package com.mortgages.bslayer.response;

import com.mortgages.bslayer.entity.MortgageApplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rameshkale on 13/08/20.
 */
public class MortgageApplicationsResponse implements Serializable {
    private List<MortgageApplication> data;

    public List<MortgageApplication> getData() {
        return data;
    }

    public void setData(List<MortgageApplication> data) {
        this.data = data;
    }
}

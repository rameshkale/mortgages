package com.mortgages.bslayer.request;

import com.mortgages.bslayer.entity.MortgageApplication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by rameshkale on 13/08/20.
 */

public class NewApplicationsRequest implements Serializable {

    private MortgageApplication data;

    public MortgageApplication getData() {
        return data;
    }

    public void setData(MortgageApplication data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewApplicationsRequest{" +
                "data=" + data +
                '}';
    }
}

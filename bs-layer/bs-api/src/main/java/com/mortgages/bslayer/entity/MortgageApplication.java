package com.mortgages.bslayer.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by rameshkale on 08/08/20.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"id", "version", "offerId", "productId", "offerDate", "createdDate", "expired"}
)
@XmlRootElement(
        name = "newMortgageApplicationsRequest"
)
public class MortgageApplication implements Serializable {
    private String id;
    private Integer version;
    private String offerId;
    private String productId;
    private String offerDate;
    private String createdDate;
    private Boolean expired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MortgageApplication that = (MortgageApplication) o;

        if (!id.equals(that.id)) return false;
        if (!version.equals(that.version)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MortgageApplication{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", offerId='" + offerId + '\'' +
                ", productId='" + productId + '\'' +
                ", offerDate='" + offerDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", expired='" + expired + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }
}

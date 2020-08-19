package com.mortgages.dslayer.engine.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rameshkale on 13/08/20.
 */
public class Application {
    public String id;
    public int ver;
    public String offrId;
    public String prdId;
    public String offrDt;
    public String creDt;
    public boolean expired = Boolean.FALSE;

    private transient static SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

    public Application(String id, int ver) {
        this.id = id;
        this.ver = ver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public String getOffrId() {
        return offrId;
    }

    public void setOffrId(String offrId) {
        this.offrId = offrId;
    }

    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getOffrDt() {
        return offrDt;
    }

    public void setOffrDt(String offrDt) {
        this.offrDt = offrDt;
    }

    public String getCreDt() {
        return creDt;
    }

    public void setCreDt(String creDt) {
        this.creDt = creDt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Long getOfferDate() throws ParseException {
        return sdf.parse(offrDt).getTime();
    }
    public Long getCreatedDate() throws ParseException {
        return sdf.parse(creDt).getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (ver != that.ver) return false;
        if (expired != that.expired) return false;
        if (!id.equals(that.id)) return false;
        if (!offrId.equals(that.offrId)) return false;
        if (prdId != null ? !prdId.equals(that.prdId) : that.prdId != null) return false;
        if (offrDt != null ? !offrDt.equals(that.offrDt) : that.offrDt != null) return false;
        return creDt != null ? creDt.equals(that.creDt) : that.creDt == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ver;
        return result;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", ver=" + ver +
                ", offrId='" + offrId + '\'' +
                ", prdId='" + prdId + '\'' +
                ", offrDt='" + offrDt + '\'' +
                ", creDt='" + creDt + '\'' +
                ", expired=" + expired +
                '}';
    }
}

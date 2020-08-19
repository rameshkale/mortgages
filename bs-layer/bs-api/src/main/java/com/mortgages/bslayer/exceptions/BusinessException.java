package com.mortgages.bslayer.exceptions;

/**
 * Created by rameshkale on 14/08/20.
 */
public class BusinessException {
    public String code;
    public String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

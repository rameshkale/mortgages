package com.mortgages.dslayer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rameshkale on 14/08/20.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {
    private String code;
    private String description;
    public enum BusinessError {ERROR_101, ERROR_102}
    public BusinessException(BusinessError code, String description) {
        super();
        this.code = code.toString();
        this.description = description;
    }

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
    public String toJsonString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"description\":\"" + description + '\"' +
                '}';
    }
}

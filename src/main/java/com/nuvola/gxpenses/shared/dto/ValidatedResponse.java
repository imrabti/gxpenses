package com.nuvola.gxpenses.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class ValidatedResponse<T> implements Dto {

    private T data;
    private Map<String, String> errors;

    public ValidatedResponse() {
    }

    public ValidatedResponse(T data, Map<String, String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}

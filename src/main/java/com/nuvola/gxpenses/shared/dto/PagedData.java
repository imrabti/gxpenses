package com.nuvola.gxpenses.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PagedData<T> implements Dto {

    private List<T> data;
    private Integer totalElements;

    public PagedData() {
    }

    public PagedData(List<T> data, Integer totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

}

package com.nuvola.gxpenses.shared.domaine;

import com.nuvola.gxpenses.shared.dto.Dto;
import com.nuvola.gxpenses.shared.type.FrequencyType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Budget implements Dto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated
    private FrequencyType periodicity;
    @Transient
    private Double totalAlowed;
    @Transient
    private Double totalConsumed;
    @Transient
    private Integer purcentageConsumed;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FrequencyType getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(FrequencyType periodicity) {
        this.periodicity = periodicity;
    }

    public Double getTotalAlowed() {
        return totalAlowed;
    }

    public void setTotalAlowed(Double totalAlowed) {
        this.totalAlowed = totalAlowed;
    }

    public Double getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(Double totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public Integer getPurcentageConsumed() {
        return purcentageConsumed;
    }

    public void setPurcentageConsumed(Integer purcentageConsumed) {
        this.purcentageConsumed = purcentageConsumed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

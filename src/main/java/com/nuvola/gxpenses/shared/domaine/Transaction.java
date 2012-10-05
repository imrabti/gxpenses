package com.nuvola.gxpenses.shared.domaine;

import com.nuvola.gxpenses.shared.dto.Dto;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class Transaction implements Dto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    private String payee;
    @Enumerated
    private TransactionType type;
    private Date date;
    private Double amount;
    private String tags;
    @ManyToOne
    private Account account;
    @JsonBackReference
    @ManyToOne
    private Transaction destTransaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getDestTransaction() {
        return destTransaction;
    }

    public void setDestTransaction(Transaction destTransaction) {
        this.destTransaction = destTransaction;
    }

}

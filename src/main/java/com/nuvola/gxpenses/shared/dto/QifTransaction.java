package com.nuvola.gxpenses.shared.dto;

public class QifTransaction implements Dto {

    private String number;
    private String amount;
    private String date;
    private String memo;
    private String cleared;
    private String payee;
    private String address;
    private String category;
    private String flag;
    private String investment;
    private String investmentSecurity;
    private String investmentPrice;
    private String investmentSharesQuantity;
    private String investmentCommission;
    private String splitCategory;
    private String splitMemo;
    private String splitPercentage;
    private String splitOrInvestmentAmount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCleared() {
        return cleared;
    }

    public void setCleared(String cleared) {
        this.cleared = cleared;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    public String getInvestmentSecurity() {
        return investmentSecurity;
    }

    public void setInvestmentSecurity(String investmentSecurity) {
        this.investmentSecurity = investmentSecurity;
    }

    public String getInvestmentPrice() {
        return investmentPrice;
    }

    public void setInvestmentPrice(String investmentPrice) {
        this.investmentPrice = investmentPrice;
    }

    public String getInvestmentSharesQuantity() {
        return investmentSharesQuantity;
    }

    public void setInvestmentSharesQuantity(String investmentSharesQuantity) {
        this.investmentSharesQuantity = investmentSharesQuantity;
    }

    public String getInvestmentCommission() {
        return investmentCommission;
    }

    public void setInvestmentCommission(String investmentCommission) {
        this.investmentCommission = investmentCommission;
    }

    public String getSplitCategory() {
        return splitCategory;
    }

    public void setSplitCategory(String splitCategory) {
        this.splitCategory = splitCategory;
    }

    public String getSplitMemo() {
        return splitMemo;
    }

    public void setSplitMemo(String splitMemo) {
        this.splitMemo = splitMemo;
    }

    public String getSplitPercentage() {
        return splitPercentage;
    }

    public void setSplitPercentage(String splitPercentage) {
        this.splitPercentage = splitPercentage;
    }

    public String getSplitOrInvestmentAmount() {
        return splitOrInvestmentAmount;
    }

    public void setSplitOrInvestmentAmount(String splitOrInvestmentAmount) {
        this.splitOrInvestmentAmount = splitOrInvestmentAmount;
    }

}

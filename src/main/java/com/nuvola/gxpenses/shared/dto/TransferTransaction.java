package com.nuvola.gxpenses.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferTransaction implements Dto  {

	private Long sourceAccount;
	private Long targetAccount;
	private Double amount;
	
	public Long getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(Long sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	public Long getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(Long targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}

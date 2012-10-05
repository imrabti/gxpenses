package com.nuvola.gxpenses.shared.dto;

import com.nuvola.gxpenses.shared.domaine.Account;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferTransaction implements Dto  {

	private Account sourceAccount;
	private Account targetAccount;
	private Double amount;
	
	public Account getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	public Account getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}

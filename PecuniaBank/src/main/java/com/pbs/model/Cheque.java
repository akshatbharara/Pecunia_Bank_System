package com.pbs.model;
import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * @author Akshat Bharara
 * Description: It is a bean class for Cheque.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Entity
public class Cheque {


	@Id
	@Column(name="cheque_number")
	int chequeNumber;
	
	@Column(name="amount")
	double amount;
	

	@Column(name="bank_name")
	String bankName;
	 
	@Column(name="ifsc")
	String ifsc;
	
	@Column(name="chequedate")
	LocalDate chequeDate;

	@Column(name="benificiary_account")
	Long benificiaryAccount;
	
	@Column(name="payee_account")
	Long payeeAccount;
	
	@Column(name="payee_name")
	String payeeName;
	
	public Cheque(int chequeNumber,double amount,String bankName, String ifsc, LocalDate chequeDate, Long benificiaryAccount, Long payeeAccount, String payeeName)
	{
		this.chequeNumber=chequeNumber;
		this.bankName=bankName;
		this.ifsc=ifsc;
		this.chequeDate=chequeDate;
		this.benificiaryAccount=benificiaryAccount;
		this.payeeAccount=payeeAccount;
		this.payeeName=payeeName;
		this.amount=amount;
	}
	public Cheque() {}
	
	public Long getBenificiaryAccount() {
		return benificiaryAccount;
	}

	public void setBenificiaryAccount(Long benificiaryAccount) {
		this.benificiaryAccount = benificiaryAccount;
	}


	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Long getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(Long payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public int getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public LocalDate getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(LocalDate chequeDate) {
		this.chequeDate = chequeDate;
	}
	
} 
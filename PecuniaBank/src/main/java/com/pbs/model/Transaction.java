package com.pbs.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author Akshat Bharara
 * Description: It is a bean class for Transactions.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	Long transactionId;
	
	@Column(name="transaction_type")
	String transactionType;
	
	@Column(name="transacton_mode")
	String transactionMode;
	
	@Column(name="transaction_date")
	LocalDate transactionDate;



	@Column(name="transaction_amount")
	double transactionAmount;
	
	@Column(name="benificiary_account")
	Long benificiaryAccount;
	
	@Column(name="payee_account")
	Long payeeAccount;
	


	public Transaction(Long transactionId,String transactionType, String transactionMode, LocalDate transactionDate, double transactionAmount,Long benificiaryAccount, Long payeeAccount)
	{
		
		this.transactionId=transactionId;
		this.transactionDate=transactionDate;
		this.transactionMode=transactionMode;
		this.transactionType=transactionType;
		this.transactionAmount=transactionAmount;
		this.benificiaryAccount=benificiaryAccount;
		this.payeeAccount=payeeAccount;
	}
	

	
	public double getTransactionAmount() {
		return transactionAmount;
	}



	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}



	public Transaction() {}
	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}



	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}



	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	public Long getBenificiaryAccount() {
		return benificiaryAccount;
	}



	public void setBenificiaryAccount(Long benificiaryAccount) {
		this.benificiaryAccount = benificiaryAccount;
	}



	public Long getPayeeAccount() {
		return payeeAccount;
	}



	public void setPayeeAccount(Long payeeAccount) {
		this.payeeAccount = payeeAccount;
	}



}
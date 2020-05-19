package com.pbs.dao;

import java.time.LocalDate;

import com.pbs.model.Account;
import com.pbs.model.Cheque;

public interface ITransactionDao {
	
	public void saveChequeDetails(Cheque currentChequeDetails);
	public Account getBenificiaryAccount(Long benificiaryAccount) throws Exception;
	public Account getPayeeAccount(Long payeeAccount)  throws Exception;
	public void saveTransaction(Long transactionId,Long benificaryAccount, Long payeeAccount, String transactionMode,double amount, String transactionType,LocalDate transactionDate);
	public void saveAccountById(Account account);
	public void deleteAccountById(Long accountNumber); 
}

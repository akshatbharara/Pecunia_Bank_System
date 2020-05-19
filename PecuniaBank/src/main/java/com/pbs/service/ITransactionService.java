package com.pbs.service;

import com.pbs.model.Account;
import com.pbs.model.Cheque;
import com.pbs.model.SlipTransaction;

public interface ITransactionService {
	
	
	public void creditUsingSlip(SlipTransaction slip) throws Exception;
	public void transactCreditSlip(Account benificiaryAccountDetails, double amount);
	public void creditUsingCheque(Cheque currentChequeDetails) throws Exception;
	public void transactCreditCheque(Account benificiaryAccountDetails, Account payeeAccountDetails, double amount) throws Exception;
	public void saveCredit(Long benificaryAccount, Long payeeAccount, String transactionMode,double amount, String transactionType);
	public void debitUsingSlip(SlipTransaction slip) throws Exception;
	public void transactDebitSlip(Account benificiaryAccountDetails, double amount) throws Exception ;
	public void debitUsingCheque(Cheque cheque) throws Exception;
	public void transactDebitCheque(Account benificiaryAccountDetails, Account payeeAccountDetails, double amount) throws Exception;
	public void saveDebit(Long benificaryAccount, Long payeeAccount, String transactionMode,double amount, String transactionType);
	public Account getBenificiaryAccount(Long benificiaryAccount) throws Exception;
	public Account getPayeeAccount(Long payeeAccount) throws Exception;
}
	
		
	
	

	
	
	
	
	
	

package com.pbs.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pbs.dao.ITransactionDao;
import com.pbs.model.Account;
import com.pbs.model.Cheque;
import com.pbs.model.SlipTransaction;

/**
 * @author Akshat Bharara
 * Description: It is a Rest full service class that provides the services for Transaction.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	ITransactionDao transactionDao;
	
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: creditUsingSlip
	 * Method Description: Credits balance in customer's account using slip
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void creditUsingSlip(SlipTransaction slip) throws Exception {
		// TODO Auto-generated method stub
		Account benificiaryAccountDetails =getBenificiaryAccount(slip.getAccountNumber());
		transactCreditSlip(benificiaryAccountDetails,slip.getAmount());
		saveCredit(slip.getAccountNumber(), slip.getAccountNumber(), "Slip", slip.getAmount(),"Credit" );
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: transactCreditSlip
	 * Method Description: Perform Transaction(Credit) like amount deduction and addition using Slip.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void transactCreditSlip(Account benificiaryAccountDetails, double amount) {
		// TODO Auto-generated method stub
		double benificiaryBalance = benificiaryAccountDetails.getBalance();
		benificiaryBalance = benificiaryBalance+amount;
		benificiaryAccountDetails.setBalance(benificiaryBalance);
		transactionDao.deleteAccountById(benificiaryAccountDetails.getAccountNumber());
		transactionDao.saveAccountById(benificiaryAccountDetails);
		}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: creditUsingCheque
	 * Method Description: Credits balance in customer's account using cheque
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void creditUsingCheque(Cheque cheque) throws Exception {
		
			transactionDao.saveChequeDetails(cheque);
			Account benificiaryAccountDetails =getBenificiaryAccount(cheque.getBenificiaryAccount());
			Account payeeAccountDetails =getPayeeAccount(cheque.getPayeeAccount());
			transactCreditCheque(benificiaryAccountDetails,payeeAccountDetails,cheque.getAmount());
			saveCredit(cheque.getBenificiaryAccount(),cheque.getPayeeAccount(),"Cheque", cheque.getAmount(), "Credit");
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: transactCreditCheque
	 * Method Description: Perform Transaction(Credit) like amount deduction and addition using Cheque.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void transactCreditCheque(Account benificiaryAccountDetails, Account payeeAccountDetails,double amount) throws Exception {
		
		double payeeBalance = payeeAccountDetails.getBalance();
		double benificiaryBalance = benificiaryAccountDetails.getBalance();
		if(payeeBalance<amount) {
			throw new Exception("Cheque Bounced, Please add balance to perform Transactions");
		}
		if(benificiaryAccountDetails==payeeAccountDetails) {
			throw new Exception("Account Number can not be Same");
		}
		payeeBalance = payeeBalance-amount;
		benificiaryBalance = benificiaryBalance+amount;
		benificiaryAccountDetails.setBalance(benificiaryBalance);
		transactionDao.deleteAccountById(benificiaryAccountDetails.getAccountNumber());
		transactionDao.saveAccountById(benificiaryAccountDetails);
		payeeAccountDetails.setBalance(payeeBalance);
		transactionDao.deleteAccountById(payeeAccountDetails.getAccountNumber());
		transactionDao.saveAccountById(payeeAccountDetails);
		
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: saveCredit
	 * Method Description: Save Credit Transaction in Database
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void saveCredit(Long benificaryAccount, Long payeeAccount, String transactionMode, double amount,
			String transactionType) {
		// TODO Auto-generated method stub
		LocalDate transactionDate=LocalDate.now();
		Long transactionId = null ;
		//LocalTime transactionTime=LocalTime.now();
		transactionDao.saveTransaction(transactionId,benificaryAccount, payeeAccount, transactionMode, amount, transactionType,transactionDate);

	}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: debitUsingSlip
	 * Method Description: Debit balance from customer's account using slip
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void debitUsingSlip(SlipTransaction slip) throws Exception {
		// TODO Auto-generated method stub
		Account benificiaryAccountDetails =transactionDao.getBenificiaryAccount(slip.getAccountNumber());
		transactDebitSlip(benificiaryAccountDetails,slip.getAmount());
		saveDebit(slip.getAccountNumber(),slip.getAccountNumber(), "Slip",slip.getAmount() ,"Debit" );
		}
	/**
	 * @author : Akshat Bharara
	 * Method Name: transactDebitSlip
	 * Method Description: Perform Transaction(Debit) like amount deduction using Slip.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void transactDebitSlip(Account benificiaryAccountDetails, double amount)
			throws Exception {
		double benificiaryBalance = benificiaryAccountDetails.getBalance();
		if(benificiaryBalance<amount) {
			throw new Exception("Insufficient Balance");
		}
		benificiaryBalance = benificiaryBalance-amount;
		benificiaryAccountDetails.setBalance(benificiaryBalance);
		transactionDao.deleteAccountById(benificiaryAccountDetails.getAccountNumber());
		transactionDao.saveAccountById(benificiaryAccountDetails);

	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: debitUsingCheque
	 * Method Description: Debit balance from customer's account using cheque
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void debitUsingCheque(Cheque cheque) throws Exception {
		// TODO Auto-generated method stub
		transactionDao.saveChequeDetails(cheque);
		Account benificiaryAccountDetails =transactionDao.getBenificiaryAccount(cheque.getBenificiaryAccount());
		Account payeeAccountDetails =transactionDao.getPayeeAccount(cheque.getPayeeAccount());
		transactDebitCheque(benificiaryAccountDetails,payeeAccountDetails,cheque.getAmount());
		saveDebit(cheque.getBenificiaryAccount(),cheque.getPayeeAccount(),"Cheque(Self)", cheque.getAmount(), "Debit");
		
	}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: transactDebitCheque
	 * Method Description: Perform Transaction(Debit) like amount deduction and addition using Cheque.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void transactDebitCheque(Account benificiaryAccountDetails, Account payeeAccountDetails, double amount)
			throws Exception {
		// TODO Auto-generated method stub
		double payeeBalance = payeeAccountDetails.getBalance();
		double benificiaryBalance = benificiaryAccountDetails.getBalance();
		if(benificiaryAccountDetails.getAccountNumber() != payeeAccountDetails.getAccountNumber()) {
			throw new Exception("Self Debit Transaction should have same account numbers");
		}
		if(payeeBalance<amount) {
			throw new Exception("Insufficient Balance");
		}
		payeeBalance = payeeBalance-amount;
		benificiaryBalance = benificiaryBalance+amount;
		benificiaryAccountDetails.setBalance(benificiaryBalance);
		transactionDao.deleteAccountById(benificiaryAccountDetails.getAccountNumber());
		
		transactionDao.saveAccountById(benificiaryAccountDetails);
		payeeAccountDetails.setBalance(payeeBalance);
		transactionDao.deleteAccountById(payeeAccountDetails.getAccountNumber());
		transactionDao.saveAccountById(payeeAccountDetails);

	}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: saveDebit
	 * Method Description: Save Debit Transactions in Database
	 * Method Last Modified: 07-May-2020
	 */
	public void saveDebit(Long benificaryAccount, Long payeeAccount, String transactionMode,double amount, String transactionType) {
		LocalDate transactionDate=LocalDate.now();
		Long transactionId = null;
		transactionDao.saveTransaction(transactionId,benificaryAccount, payeeAccount, transactionMode, amount, transactionType,transactionDate);
		
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: getBenificiaryAccount
	 * Method Description: To get Benificiary's Account Details(Account balance).
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public Account getBenificiaryAccount(Long benificiaryAccount) throws Exception {
		// TODO Auto-generated method stub
		Account benificiaryAccountDetails = transactionDao.getBenificiaryAccount(benificiaryAccount);
		return benificiaryAccountDetails;
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: getPayeeAccount
	 * Method Description: To get Payee's Account Details(Account balance).
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public Account getPayeeAccount(Long payeeAccount) throws Exception {
		// TODO Auto-generated method stub
		Account payeeAccountDetails = transactionDao.getBenificiaryAccount(payeeAccount);

		return payeeAccountDetails;
	}

}

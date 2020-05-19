package com.pbs.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pbs.model.Account;
import com.pbs.model.Cheque;
import com.pbs.model.Transaction;
/**
 * @author Akshat Bharara
 * Description: It is DAO class that helps to Store Data for Transactions.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Component
public class TransactionDaoImpl implements ITransactionDao {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	IChequeDao chequeDao;
	@Autowired
	IAccountDao accountDao;
	/**
	 * @author : Akshat Bharara
	 * Method Name: saveChequeDetails
	 * Method Description: Save Cheque Details in Cheque table.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void saveChequeDetails(Cheque Cheque) {
		chequeDao.save(Cheque);
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: deleteAccountById
	 * Method Description: Delete account by using its ID from database.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void deleteAccountById(Long accountNumber) {
		accountDao.deleteById(accountNumber);
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: saveAccountById
	 * Method Description: Save account by using its ID from database.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void saveAccountById(Account account) {
		// TODO Auto-generated method stub
		accountDao.save(account);
	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: getBenificiaryAccount
	 * Method Description: Get benificiary details from databse using account ID.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public Account getBenificiaryAccount(Long benificiaryAccount) throws Exception {
		Account benificiaryAccountDetails = accountDao.findById(benificiaryAccount)
		          .orElseThrow(() -> new Exception("Invalid Account ID" + benificiaryAccount));
		        return benificiaryAccountDetails;

	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: getPayeeAccount
	 * Method Description: Get payee details from databse using account ID.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public Account getPayeeAccount(Long payeeAccount) throws Exception {
		Account payeeAccountDetails = accountDao.findById(payeeAccount)
				.orElseThrow(() -> new Exception("Invalid Account ID" + payeeAccount));
		return payeeAccountDetails;

	}
	/**
	 * @author : Akshat Bharara
	 * Method Name: saveTransaction
	 * Method Description: Save all Transaction related details in the database.
	 * Method Last Modified: 07-May-2020
	 */
	@Override
	public void saveTransaction(Long transactionId, Long benificaryAccount, Long payeeAccount, String transactionMode,
			double amount, String transactionType, LocalDate transactionDate) {
			transactionRepository.save(new Transaction(transactionId, transactionType, transactionMode, transactionDate,
				amount, benificaryAccount, payeeAccount));
	}

}

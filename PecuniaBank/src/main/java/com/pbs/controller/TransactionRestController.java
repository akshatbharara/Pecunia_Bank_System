package com.pbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbs.dao.ITransactionDao;
import com.pbs.model.Account;
import com.pbs.model.Cheque;
import com.pbs.model.SlipTransaction;
import com.pbs.model.Transaction;
import com.pbs.service.ITransactionService;

/**
 * @author Akshat Bharara
 * Description: It is a Rest full controller class that helps to use the services for Transaction.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	ITransactionService transactionService;
	@Autowired
	ITransactionDao transactionDao;
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: debitUsingSlip
	 * Method Description: Debit balance from customer's account using slip
	 * Method Last Modified: 07-May-2020
	 */
	@PostMapping("/slipdebit")
	public void debitUsingSlip(@RequestBody SlipTransaction slip)
			throws Exception {

		transactionService.debitUsingSlip(slip);
	}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: creditUsingSlip
	 * Method Description: Credits balance in customer's account using slip
	 * Method Last Modified: 07-May-2020
	 */

	@PostMapping("/slipcredit")
	public void creditUsingSlip(@RequestBody SlipTransaction slip)
			throws Exception {
		transactionService.creditUsingSlip(slip);
	}
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: debitUsingCheque
	 * Method Description: Debit balance from customer's account using cheque
	 * Method Last Modified: 07-May-2020
	 */

	@PostMapping("/chequedebit")
	public void debitUsingCheque(@RequestBody Cheque cheque, Long payeeAccount)
			throws Exception {
		transactionService.debitUsingCheque(cheque);
	}
	
	
	/**
	 * @author : Akshat Bharara
	 * Method Name: creditUsingCheque
	 * Method Description: Credits balance in customer's account using cheque
	 * Method Last Modified: 07-May-2020
	 */
	@PostMapping("/chequecredit")
	public void creditUsingCheque(@RequestBody Cheque cheque, Account payeeAccountDetails)
			throws Exception {
		transactionService.creditUsingCheque(cheque);
		Transaction transaction = new Transaction();
		double payeeBalance = payeeAccountDetails.getBalance();
		if(payeeBalance<transaction.getTransactionAmount()) {
			throw new Exception("Cheque Bounced, Please add balance to perform Transactions");
		}
	}
	
	@ExceptionHandler(Exception.class)
	public String inValid(Exception e) {
		return e.getMessage();
	}
}

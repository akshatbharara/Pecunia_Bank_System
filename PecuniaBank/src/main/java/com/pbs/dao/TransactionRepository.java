package com.pbs.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbs.model.Transaction;
/**
 * @author Akshat Bharara
 * Description: It is a Rest full Repository class that helps to Store Data for Transactions.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Repository
@Transactional
public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}

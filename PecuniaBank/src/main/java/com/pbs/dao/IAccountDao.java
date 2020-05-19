package com.pbs.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbs.model.Account;
/**
 * @author Akshat Bharara
 * Description: It is a Rest full Repository class that helps to Store Data for Accounts.
 * version: 1.0
 * Last Modified Date: 07-May-2020
 *
 */
@Repository
public interface IAccountDao extends CrudRepository<Account, Long>{

}

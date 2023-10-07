package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Account;

public interface AccountService {

//    READ
    Account getById(Long id);

//    CREATE
    Account newAccount(Account account);

//    UPDATE
    Account updateAccount(Long id, Account account);

//    DELETE
    boolean deleteAccount(Long id);
}

package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.repository.AccountRepository;
import com.linhv.scheduling.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepo;

    @Override
    public Account getById(Long id) {
        return accountRepo.findById(id).get();
    }

    @Override
    public Account newAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return null;
    }

    @Override
    public boolean deleteAccount(Long id) {
        try {
            Account account = this.getById(id);
            accountRepo.delete(account);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}

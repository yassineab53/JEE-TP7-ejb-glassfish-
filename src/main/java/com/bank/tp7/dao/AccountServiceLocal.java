package com.bank.tp7.dao;


import com.bank.tp7.entity.Account;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AccountServiceLocal {
    void createAccount(Account account);
    List<Account> findAllAccounts();
    void deposit(Long accountId, Double amount);
}

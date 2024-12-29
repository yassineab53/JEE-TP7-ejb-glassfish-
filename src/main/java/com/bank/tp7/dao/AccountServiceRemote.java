package com.bank.tp7.dao;

import com.bank.tp7.entity.Account;

import java.util.List;

import jakarta.ejb.Remote;

@Remote
public interface AccountServiceRemote {
    void createAccount(Account account);
    List<Account> findAllAccounts();
    void deposit(Long accountId, Double amount);
    /* … Ajouter d’autres méthodes ici */
}

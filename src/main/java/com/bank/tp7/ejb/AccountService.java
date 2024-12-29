package com.bank.tp7.ejb;


import com.bank.tp7.dao.AccountServiceLocal;
import com.bank.tp7.dao.AccountServiceRemote;
import com.bank.tp7.entity.Account;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class AccountService implements AccountServiceLocal, AccountServiceRemote {
    @PersistenceContext(unitName = "BankPU")
    private EntityManager em;
    public void createAccount(Account account) {
        em.persist(account);
    }
    public Account findAccountById(Long id) {
        return em.find(Account.class, id);
    }
    public List<Account> findAllAccounts() {
        return em.createQuery("SELECT a FROM Account a",
                Account.class).getResultList();
    }

    public void deposit(Long id, Double amount) {
        Account account = em.find(Account.class, id);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            em.merge(account);
        }
    }

    public void withdraw(Long id, Double amount) throws Exception {
        Account account = em.find(Account.class, id);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                em.merge(account);
            } else {
                throw new Exception("Solde insuffisant.");
            }
        }
    }

    public void transfer(Long fromId, Long toId, Double amount) throws Exception {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }
}

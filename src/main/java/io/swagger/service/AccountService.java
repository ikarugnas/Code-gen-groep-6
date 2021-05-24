package io.swagger.service;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.CreateAccount;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public AccountService(){}

    public AccountWithTransactions createAccount(CreateAccount incAccount){
        AccountWithTransactions createAccount = new AccountWithTransactions(incAccount.getAbsoluteLimit(), incAccount.getActive(), incAccount.getOwner(), incAccount.getType());

        accountRepository.save(createAccount);

        return accountRepository.findAll().get(0);
    }

}

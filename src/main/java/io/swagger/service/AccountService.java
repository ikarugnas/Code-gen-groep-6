package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public AccountService(){
    }

    public void createBankAccount(AccountWithTransactions bankAccount){
        accountRepository.save(bankAccount);
    }

    public AccountWithTransactions createNewAccount(CreateAccount incAccount){
        AccountWithTransactions createNewAccount = new AccountWithTransactions(CreateIBAN(), incAccount.getAbsoluteLimit(), incAccount.getActive(), userRepository.findByUsername(incAccount.getOwner()) , incAccount.getType());

        accountRepository.save(createNewAccount);

        return createNewAccount;
    }

    public AccountWithTransactions getAccountByIban(String iban){
        return accountRepository.findAccountWithTransactionsByIban(iban);
    }

    public List<AccountWithTransactions> getAllAccounts(){
        return accountRepository.findAll();
    }

//    public List<AccountWithTransactions> getAccountsWithOffset(long offset, long limit){
//        return accountRepository.findTopByIbanBetween(offset, limit);
//    }

    public List<AccountWithTransactions> getAllAccountsByUser(User user){
        return accountRepository.findAllAccountsByOwner(user);
    }

    public String CreateIBAN(){
        String IBAN = "";
        IBAN += "NL";

        for (int i = 0; i < 2; i++) {
            int rand = (int)(Math.random() * 10);
            IBAN += rand;
        }

        IBAN += "INHO";

        for (int i = 0; i < 10; i++) {
            int rand = (int)(Math.random() * 10);
            IBAN += rand;
        }

        AccountWithTransactions a = accountRepository.findAccountWithTransactionsByIban(IBAN);

        if (accountRepository.findAccountWithTransactionsByIban(IBAN) != null) {
            return CreateIBAN();
        }

        return IBAN;
    }

    public boolean accountExist(String iban) {
        return (accountRepository.findAccountWithTransactionsByIban(iban) != null);
    }


    public AccountWithTransactions updateAccount(UpdateAccountDTO newAccount,AccountWithTransactions accountToBeUpdated){

        accountToBeUpdated.setActive(newAccount.status);
        accountToBeUpdated.setAbsoluteLimit(newAccount.absoluteLimit);

        accountRepository.save(accountToBeUpdated);

        return accountToBeUpdated;

    }
}

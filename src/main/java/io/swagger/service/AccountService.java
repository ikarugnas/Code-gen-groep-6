package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public AccountService() {
    }

    // create bank account with static iban
    public void createBankAccount(AccountWithTransactions bankAccount) {
        accountRepository.save(bankAccount);
    }

    // create account with given CreateAccount
    public AccountWithTransactions createNewAccount(CreateAccount incAccount) {
        AccountType type = AccountType.valueOf(incAccount.getType());
        AccountWithTransactions createNewAccount = new AccountWithTransactions(CreateIBAN(), incAccount.getAbsoluteLimit(), incAccount.getActive(), userRepository.findByUsername(incAccount.getOwner()), type, incAccount.getCurrency());
        accountRepository.save(createNewAccount);
        return createNewAccount;
    }

    // get account by iban
    public AccountWithTransactions getAccountByIban(String iban) {
        return accountRepository.findAccountWithTransactionsByIban(iban);
    }

    // get all accounts
    public List<AccountWithTransactions> getAllAccounts(Long offset, Long limit) {
        long defaultOffset = 0;
        long defaultLimit = 10;
        if (offset == null) {
            offset = defaultOffset;
        }
        if (limit == null) {
            limit = defaultLimit;
        }
        Pageable pageable = PageRequest.of(offset.intValue(), limit.intValue());
        return accountRepository.findAllAccounts(pageable);
    }

    // get all accounts for given user
    public List<AccountWithTransactions> getAllAccountsByUser(User user) {
        return accountRepository.findAllAccountsByOwner(user);
    }

    // get account for given UUID
    public List<AccountWithTransactions> getAllAccountsByUserid(Long offset, Long limit, UUID Id) {
        long defaultOffset = 0;
        long defaultLimit = 10;
        if (offset == null) {
            offset = defaultOffset;
        }
        if (limit == null) {
            limit = defaultLimit;
        }
        Pageable pageable = PageRequest.of(offset.intValue(), limit.intValue());
        return deleteInhollandAccountFromList(accountRepository.findAccountsById(Id, pageable));
    }

    // Create random iban within criteria
    public String CreateIBAN() {
        String IBAN = "";
        IBAN += "NL";
        for (int i = 0; i < 2; i++) {
            int rand = (int) (Math.random() * 10);
            IBAN += rand;
        }
        IBAN += "INHO";
        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * 10);
            IBAN += rand;
        }
        AccountWithTransactions a = accountRepository.findAccountWithTransactionsByIban(IBAN);
        if (accountRepository.findAccountWithTransactionsByIban(IBAN) != null) {
            return CreateIBAN();
        }
        return IBAN;
    }

    // check if accounts exists
    public boolean accountExist(String iban) {
        return (accountRepository.findAccountWithTransactionsByIban(iban) != null);
    }

    // update account
    public AccountWithTransactions updateAccount(UpdateAccountDTO newAccount, AccountWithTransactions accountToBeUpdated) {
        accountToBeUpdated.setActive(newAccount.status);
        accountToBeUpdated.setAbsoluteLimit(newAccount.absoluteLimit);
        accountRepository.save(accountToBeUpdated);
        return accountToBeUpdated;

    }

    // parsing list
    public List<AllAccountsWithoutTransactions> changingFromWithToWithoutTransaction(List<AccountWithTransactions> accountWithTransactionsList) {
        deleteInhollandAccountFromList(accountWithTransactionsList);
        List<AllAccountsWithoutTransactions> allAccountsWithoutTransactionsList = new ArrayList<>();
        for (AccountWithTransactions a : accountWithTransactionsList) {
            AllAccountsWithoutTransactions b = new AllAccountsWithoutTransactions(a.getIban(), a.getBalance(), a.getType(), a.getOwner().getId(), a.getAbsoluteLimit(), a.getActive(), a.getCurrency());
            allAccountsWithoutTransactionsList.add(b);
        }
        return allAccountsWithoutTransactionsList;
    }

    // if bank account is within the list of returns remove bank account
    public List<AccountWithTransactions> deleteInhollandAccountFromList(List<AccountWithTransactions> accountWithTransactions) {
        AccountWithTransactions bankAccount = getAccountByIban("NL01INHO0000000001");
        User bank = userService.getUserByIdForAccounts(bankAccount.getOwner().getId());
        if (myUserDetailsService.getLoggedInUser().getUsername() == bank.getUsername()) {
            System.out.println("is bank");
        } else {
            accountWithTransactions.removeIf(i -> i.getIban() == bankAccount.getIban());
        }
        return accountWithTransactions;
    }


}

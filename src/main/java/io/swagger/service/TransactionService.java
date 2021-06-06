package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import io.swagger.repository.DepositRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    WithdrawalRepository withdrawalRepository;


    public TransactionService() {
    }

    public Transaction createTransaction(TransactionRequestBody transaction, String username){

        Timestamp dateAndTime = new Timestamp(new Date().getTime());
        double transactionAmount = transaction.getAmount();
        AccountWithTransactions accountFrom = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountFrom());
        AccountWithTransactions accountTo = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountTo());


        Transaction newTransaction = new Transaction(
                username,
                accountFrom,
                accountTo,
                transactionAmount,
                "Transaction",
                dateAndTime);


        accountFrom.setBalance(accountFrom.getBalance() - transactionAmount);
        accountTo.setBalance(accountTo.getBalance() + transactionAmount);


        Double currentBalance = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountFrom()).getBalance();
        Enum activeStatus = accountFrom.getActive();

        if (activeStatus == Status.Inactive){
            throw new IllegalArgumentException("Account is inactive");
        }

        else if (currentBalance < transactionAmount) {
            throw new IllegalArgumentException("Current balance is too low");
        }

        else if (transactionAmount > accountFrom.getAbsoluteLimit()) {
            throw new IllegalArgumentException("Transaction limit has been reached");
        }

        else {
            transactionRepository.save(newTransaction);
        }

        return transactionRepository.findByDate_And_Time(dateAndTime);
    }

    public Transaction createDeposit(DepositRequestBody deposit, String username){

        Timestamp dateAndTime = new Timestamp(new Date().getTime());
        double depositAmount = deposit.getAmount();
        AccountWithTransactions accountTo = accountRepository.findAccountWithTransactionsByIban(deposit.getAccountTo());
        AccountWithTransactions accountFrom = accountTo;

        Transaction newDeposit = new Transaction(
                username,
                accountFrom,
                accountTo,
                depositAmount,
                "Withdrawal",
                dateAndTime);

        Double currentBalance = accountRepository.findAccountWithTransactionsByIban(deposit.getAccountTo()).getBalance();
        Enum activeStatus = accountFrom.getActive();

        accountTo.setBalance(accountTo.getBalance() + depositAmount);

        if (activeStatus == Status.Inactive){
            throw new IllegalArgumentException("Account is inactive");
        }

        else {
            transactionRepository.save(newDeposit);
        }


        return transactionRepository.findByDate_And_Time(dateAndTime);
    }

    public Transaction createWithdrawal(WithdrawalRequestBody withdrawal, String username){

        Timestamp dateAndTime = new Timestamp(new Date().getTime());
        double withdrawalAmount = withdrawal.getAmount();
        AccountWithTransactions accountFrom = accountRepository.findAccountWithTransactionsByIban(withdrawal.getAccountFrom());
        AccountWithTransactions accountTo = accountFrom;

        Transaction newWithdrawal = new Transaction(
                username,
                accountFrom,
                accountTo,
                withdrawalAmount,
                "Withdrawal",
                dateAndTime);

        accountFrom.setBalance(accountFrom.getBalance() - withdrawalAmount);


        Double currentBalance = accountRepository.findAccountWithTransactionsByIban(withdrawal.getAccountFrom()).getBalance();
        Enum activeStatus = accountFrom.getActive();

        if (activeStatus == Status.Inactive){
            throw new IllegalArgumentException("Account is inactive");
        }

        else if (currentBalance < withdrawalAmount) {
            throw new IllegalArgumentException("Current balance is too low");
        }

        else {
            transactionRepository.save(newWithdrawal);
        }


        return transactionRepository.findByDate_And_Time(dateAndTime);
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }

//    public List<Transaction> getTransactionsByIban(String iban) {
//
//        return transactionRepository.findByIban(iban);
//    }



//    public Deposit createDeposit(DepositRequestBody deposit) {
//        Deposit newDeposit = new Deposit(deposit.getAccountTo()),
//                deposit.getAmount(),
//                deposit.
//,    }

//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
//
//    public User getTransactionByUserPerforming(String username) {
//        return transactionRepository.findUserByUsernameQuery(username);
//    }

}
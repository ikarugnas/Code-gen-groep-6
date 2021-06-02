package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.DepositRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.threeten.bp.OffsetDateTime;

@Service
public class TransactionService {

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

    public Transaction createTransaction(TransactionRequestBody transaction){

        AccountWithTransactions accountFrom = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountFrom());

        Transaction newTransaction = new Transaction(transaction.getId(),
                transaction.getUserPerforming(),
                accountFrom,
                accountRepository.findAccountWithTransactionsByIban(transaction.getAccountTo()),
                transaction.getAmount(),
                transaction.getTransactionType(),
                OffsetDateTime.parse(transaction.getDateAndTime()));

        transactionRepository.save(newTransaction);



        return transactionRepository.findByAccountFrom(transaction.getAccountFrom());
    }

    public Deposit createDeposit(DepositRequestBody deposit){

        Deposit newDeposit = new Deposit(deposit.getId(),
                deposit.getUserPerforming(),
                deposit.getAccountFrom(),
                deposit.getAccountTo(),
                deposit.getAmount(),
                deposit.getTransactionType(),
                OffsetDateTime.parse(deposit.getDateAndTime()));

        depositRepository.save(newDeposit);

        return depositRepository.findByAccountFrom(deposit.getAccountFrom());
    }

    public Withdrawal createWithdrawal(WithdrawalRequestBody withdrawal){

        Withdrawal newWithdrawal = new Withdrawal(withdrawal.getId(),
                withdrawal.getUserPerforming(),
                withdrawal.getAccountFrom(),
                withdrawal.getAccountTo(),
                withdrawal.getAmount(),
                withdrawal.getTransactionType(),
                OffsetDateTime.parse(withdrawal.getDateAndTime()));

        withdrawalRepository.save(newWithdrawal);

        return withdrawalRepository.findByAccountFrom(withdrawal.getAccountFrom());
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
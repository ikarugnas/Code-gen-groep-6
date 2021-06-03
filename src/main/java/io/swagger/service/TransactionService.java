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

        AccountWithTransactions accountFrom = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountFrom());
        double transactionAmount;



        Transaction newTransaction = new Transaction(
                username,
                accountFrom,
                accountRepository.findAccountWithTransactionsByIban(transaction.getAccountTo()),
                transaction.getAmount(),
                "Transaction",
                new Timestamp(new Date().getTime()));





//        Double currentBalance = accountRepository.findAccountWithTransactionsByIban(transaction.getAccountFrom()).getBalance();
//        Enum activeStatus = accountFrom.getActive();

//        if (activeStatus == Status.Inactive){
//            // throw inactive account error
//        }
//        else if (currentBalance < transactionAmount) {
//            /// cannot create transaction
//        }
//        else if (transactionAmount > accountFrom.getAbsoluteLimit()) {
//            // transaction higher than limit error
//        }
//        else {
            transactionRepository.save(newTransaction);
//        }

        return transactionRepository.findByAccountFrom(transaction.getAccountFrom());
    }

    public Deposit createDeposit(DepositRequestBody deposit){

        Deposit newDeposit = new Deposit(deposit.getId(),
                deposit.getUserPerforming(),
                deposit.getAccountFrom(),
                deposit.getAccountTo(),
                deposit.getAmount(),
                deposit.getTransactionType(),
                new Timestamp(new Date().getTime()));

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
                new Timestamp(new Date().getTime()));

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
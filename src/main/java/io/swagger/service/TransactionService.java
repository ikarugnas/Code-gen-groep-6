package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionService() {
    }

    public Transaction createTransaction(TransactionRequestBody transaction){
        Transaction newTransaction = new Transaction(transaction.getAccountFrom(),
                transaction.getAmount(),
                transaction.getAccountTo());

        transactionRepository.save(newTransaction);

        return transactionRepository.findAccountFrom(transaction.getAccountFrom());
    }

//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
//
//    public User getTransactionByUserPerforming(String username) {
//        return transactionRepository.findUserByUsernameQuery(username);
//    }

}
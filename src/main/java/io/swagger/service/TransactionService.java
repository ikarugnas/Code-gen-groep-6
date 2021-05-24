package io.swagger.service;

import io.swagger.model.RegisterDTO;
import io.swagger.model.Transaction;
import io.swagger.model.User;
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

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public User getTransactionByUserPerforming(String username) {
        return transactionRepository.findUserByUsernameQuery(username);
    }

}
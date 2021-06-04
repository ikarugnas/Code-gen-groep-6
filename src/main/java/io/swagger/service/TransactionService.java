package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.DepositRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                new Timestamp(new Date().getTime()));

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

    public List<TransactionReponse> getAllTransactions(Long limit, Long offset, String dateFrom, String dateTo, String transactionType) throws Exception {
        if (limit == null) {
            limit = (long) 50;
        } if (offset == null) {
            offset = (long) 0;
        }

        Pageable pageable = PageRequest.of(offset.intValue(), limit.intValue());

        if (transactionType != null) {
            if (Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)) == null) {
                throw new Exception("TransactionType is invalid");
            }
        }

        if (dateFrom == null && dateTo == null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAll(pageable));
        } else if(dateFrom != null && dateTo == null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByDateFrom(convertToTimestamp(dateFrom), pageable));
        } else if(dateFrom == null && dateTo != null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByDateTo(convertToTimestamp(dateTo), pageable));
        } else if(dateFrom == null && dateTo == null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByTransaction(Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo != null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByDateFromAndDateTo(convertToTimestamp(dateFrom), convertToTimestamp(dateTo), pageable));
        } else if(dateFrom == null && dateTo != null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByDateToAndTransactionType(convertToTimestamp(dateTo), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo == null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByDateFromAndTransactionType(convertToTimestamp(dateFrom), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo != null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByDateAndTransactionType(convertToTimestamp(dateFrom), convertToTimestamp(dateTo), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        }
        return new ArrayList<>();
    }

    public List<TransactionReponse> getAllTransactionsByIban(AccountWithTransactions account, Long limit, Long offset, String dateFrom, String dateTo, String transactionType) throws Exception {
        if (limit == null) {
            limit = (long) 50;
        } if (offset == null) {
            offset = (long) 0;
        }

        Pageable pageable = PageRequest.of(offset.intValue(), limit.intValue());

        if (transactionType != null) {
            if (Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)) == null) {
                throw new Exception("TransactionType is invalid");
            }
        }

        if (dateFrom == null && dateTo == null && transactionType == null){
            return convertPageToResponse(transactionRepository.findByIban(account, pageable));
        } else if(dateFrom != null && dateTo == null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByIbanAndDateFrom(account, convertToTimestamp(dateFrom), pageable));
        } else if(dateFrom == null && dateTo != null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByIbanAndDateTo(account, convertToTimestamp(dateTo), pageable));
        } else if(dateFrom == null && dateTo == null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByIbanAndTransaction(account, Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo != null && transactionType == null){
            return convertPageToResponse(transactionRepository.findAllByIban_DateFromAndDateTo(account ,convertToTimestamp(dateFrom), convertToTimestamp(dateTo), pageable));
        } else if(dateFrom == null && dateTo != null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByIban_DateToAndTransactionType(account, convertToTimestamp(dateTo), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo == null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByIban_DateFromAndTransactionType(account, convertToTimestamp(dateFrom), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        } else if(dateFrom != null && dateTo != null && transactionType != null){
            return convertPageToResponse(transactionRepository.findAllByIban_DateAndTransactionType(account, convertToTimestamp(dateFrom), convertToTimestamp(dateTo), Transaction.TransactionTypeEnum.fromValue(StringUtils.capitalize(transactionType)), pageable));
        }
        return new ArrayList<>();
    }

    public Timestamp convertToTimestamp(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatWithHours = new SimpleDateFormat("dd/MM/yyyy HH");

        try{
            if (date.contains(" ")) {
                return new Timestamp(dateFormatWithHours.parse(date).getTime());
            } else {
                return new Timestamp(dateFormat.parse(date).getTime());
            }
        } catch (ParseException parseException) {
            throw new Exception("Date has an invalid format");
        }
    }

    public List<TransactionReponse> convertPageToResponse(Page<Transaction> transactions){
        List<TransactionReponse> transactionReponses = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionReponse transactionReponse = new TransactionReponse(
                    transaction.getUserPerforming(),
                    transaction.getAccountFrom().getIban(),
                    transaction.getAccountTo().getIban(),
                    transaction.getAmount(),
                    transaction.getTransactionType().toString(),
                    transaction.getDateAndTime()
            );

            transactionReponses.add(transactionReponse);
        }

        return transactionReponses;
    }
}
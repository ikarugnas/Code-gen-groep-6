package io.swagger.service;

import io.swagger.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Calendar;


@Service
public class LimitService {

    @Autowired
    TransactionRepository transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(LimitService.class);

    public LimitService() {
    }

    // Check if absolute limit has been reaches
    public void checkIfBalanceIsLowerThanAbsoluteLimit(AccountWithTransactions account){

        double accountBalance = account.getBalance();
        double absoluteLimit = account.getAbsoluteLimit();

        if (accountBalance <= absoluteLimit) {
            log.error("Account balance is currently too low");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance too low, absolute limit achieved");

        }
    }

    // Check if day limit has been reached
    public void checkIfTransactionsPassDayLimit(String account, Timestamp time, User user){

        double dayLimit = user.getDayLimit();
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp queryTimeStamp = new Timestamp(cal.getTimeInMillis());
        double transactionsMadeOn1Day = transactionRepository.findSumOfTransactionsByDate_And_Time(account, queryTimeStamp);

        if (transactionsMadeOn1Day > dayLimit){
            log.error("Day limit has been reached");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Day limit has been reached");
        }
    }

    // Check if transaction limit has been reached
    public void checkIfTransactionLimitHasBeenReached(Double transactionAmount, User user) {

        double transactionLimit = user.getTransactionLimit();

        if (transactionAmount > transactionLimit){
            log.error("Transactionlimit has been reached");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction limit has been reached");
        }
    }

}

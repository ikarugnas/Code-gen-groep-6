package io.swagger.model;

import java.sql.Timestamp;

public class TransactionReponse {

    private String userPerforming;

    private String accountFrom;

    private String accountTo;

    private Double amount;

    private String transactionType;

    private Timestamp dateAndTime;

    public TransactionReponse(String userPerforming, String accountFrom, String accountTo, Double amount, String transactionType, Timestamp dateAndTime) {
        this.userPerforming = userPerforming;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.transactionType = transactionType;
        this.dateAndTime = dateAndTime;
    }

    public String getUserPerforming() {
        return userPerforming;
    }

    public void setUserPerforming(String userPerforming) {
        this.userPerforming = userPerforming;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}

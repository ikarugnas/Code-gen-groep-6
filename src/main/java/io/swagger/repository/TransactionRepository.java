package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByAccountFrom(String account);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime = ?1")
    Transaction findByDate_And_Time(Timestamp dateAndTime);

    // Query for day limit, all transactions within a day
    @Query(value = "SELECT SUM(amount) FROM Transaction t WHERE t.ACCOUNT_FROM_IBAN = ?1 AND t.DATE_AND_TIME >= ?2", nativeQuery = true)
    Double findSumOfTransactionsByDate_And_Time(String iban, Timestamp date);

//    List<Transaction> findByAccount(String iban);
    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime <= ?1")
    Page<Transaction> findAllByDateTo(Timestamp dateTo, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime >= ?1")
    Page<Transaction> findAllByDateFrom(Timestamp dateFrom, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.transactionType = ?1")
    Page<Transaction> findAllByTransaction(Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime >= ?1 AND t.dateAndTime <= ?2")
    Page<Transaction> findAllByDateFromAndDateTo(Timestamp dateFrom, Timestamp dateTo, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime <= ?1 AND t.transactionType = ?2")
    Page<Transaction> findAllByDateToAndTransactionType(Timestamp dateTo, Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime >= ?1 AND t.transactionType = ?2")
    Page<Transaction> findAllByDateFromAndTransactionType(Timestamp dateFrom, Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime >= ?1 AND t.dateAndTime <= ?2 AND t.transactionType = ?3")
    Page<Transaction> findAllByDateAndTransactionType(Timestamp dateFrom, Timestamp dateTo, Transaction.TransactionTypeEnum transactionType , Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.accountFrom = ?1 OR t.accountTo = ?1")
    Page<Transaction> findByIban(AccountWithTransactions account, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime >= ?2")
    Page<Transaction> findAllByIbanAndDateFrom(AccountWithTransactions account, Timestamp dateFrom, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime <= ?2")
    Page<Transaction> findAllByIbanAndDateTo(AccountWithTransactions account, Timestamp dateTo, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.transactionType = ?2")
    Page<Transaction> findAllByIbanAndTransaction(AccountWithTransactions account, Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime >= ?2 AND t.dateAndTime <= ?3")
    Page<Transaction> findAllByIban_DateFromAndDateTo(AccountWithTransactions account, Timestamp dateFrom, Timestamp dateTo, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime <= ?2 AND t.transactionType = ?3")
    Page<Transaction> findAllByIban_DateToAndTransactionType(AccountWithTransactions account, Timestamp dateTo, Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime >= ?2 AND t.transactionType = ?3")
    Page<Transaction> findAllByIban_DateFromAndTransactionType(AccountWithTransactions account, Timestamp dateFrom, Transaction.TransactionTypeEnum transactionType, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE (t.accountFrom = ?1 OR t.accountTo = ?1) AND t.dateAndTime >= ?2 AND t.dateAndTime <= ?3 AND t.transactionType = ?4")
    Page<Transaction> findAllByIban_DateAndTransactionType(AccountWithTransactions account, Timestamp dateFrom, Timestamp dateTo, Transaction.TransactionTypeEnum transactionType, Pageable pageable);
}




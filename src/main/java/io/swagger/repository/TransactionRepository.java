package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

//@Repository
//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//
//    Transaction findTransactionByUserPerforming(String username);
//
//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    User findUserByUsernameQuery(
//            @Param("username") String username);
//
//    Transaction findTransactionByDate(DateFormat dateFrom, DateFormat dateTo);
//
//}

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByAccountFrom(String account);

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
    Page<Transaction> findAllByFromAndTransactionType(Timestamp dateTo, Timestamp dateFrom, Transaction.TransactionTypeEnum transactionType , Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t WHERE t.accountFrom = ?1 OR t.accountTo = ?1")
    Page<Transaction> findByIban(AccountWithTransactions account, Pageable pageable);
}




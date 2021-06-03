package io.swagger.repository;

import io.swagger.model.Deposit;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

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


//    @Query(value = "SELECT t FROM TRANSACTION WHERE DATE_AND_TIME < ?1")
//    Page<Transaction> findAllByDateTo(Timestamp dateTo, Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t  WHERE t.dateAndTime > ?1")
    Page<Transaction> findAllByDateFrom(Timestamp dateTo, Pageable pageable);

//    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION WHERE DATE_AND_TIME < ?3 AND DATE_AND_TIME > ?4 LIMIT ?2 OFFSET ?1")
//    List<Transaction> findAllWithOffset_Limit_DateToAndDateFrom(Long Offset, Long Limit, Timestamp dateTo, Timestamp dateFrom);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION WHERE TRANSACTION_TYPE = ?3 LIMIT ?2 OFFSET ?1")
//    List<Transaction> findAllWithOffset_LimitAndTransactionType(Long Offset, Long Limit, String transactionType);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION WHERE DATE_AND_TIME < ?3 LIMIT ?2 OFFSET ?1")
//    List<Transaction> findAllWithOffset_Limit_DateToAndTransactionType(Long Offset, Long Limit, Timestamp dateTo, String transactionType);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION WHERE DATE_AND_TIME > ?3 LIMIT ?2 OFFSET ?1")
//    List<Transaction> findAllWithOffset_Limit_DateFromAndTransactionType(Long Offset, Long Limit, Timestamp dateFrom, String transactionType);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION WHERE DATE_AND_TIME < ?3 AND DATE_AND_TIME > ?4 LIMIT ?2 OFFSET ?1")
//    List<Transaction> findAllWithOffset_Limit_DateTo_DateFromAndTransactionType(Long Offset, Long Limit, Timestamp dateTo, Timestamp dateFrom, String transactionType);

//    List<Transaction> findByAccount(String iban);
}




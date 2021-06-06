package io.swagger.repository;

import io.swagger.model.Deposit;
import io.swagger.model.Transaction;
import io.swagger.model.User;
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

    @Query(value = "SELECT t FROM Transaction t WHERE t.dateAndTime = ?1")
    Transaction findByDate_And_Time(Timestamp dateAndTime);

//    List<Transaction> findByAccount(String iban);
}




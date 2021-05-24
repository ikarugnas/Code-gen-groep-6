package io.swagger.repository;

import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findTransactionByUserPerforming(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsernameQuery(
            @Param("username") String username);

    Transaction findTransactionByDate(DateFormat dateFrom, DateFormat dateTo);
    
}

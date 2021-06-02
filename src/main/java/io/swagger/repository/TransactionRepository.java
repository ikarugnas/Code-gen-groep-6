package io.swagger.repository;

import io.swagger.model.Deposit;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

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

    List<Transaction> findByIban(String iban);
}




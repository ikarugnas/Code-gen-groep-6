package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<AccountWithTransactions, String> {

//    AccountWithTransactions
    public AccountWithTransactions findAccountWithTransactionsByIban(String iban);

    List<AccountWithTransactions> findAllAccountsByOwner(User user);

//
//    List<AccountWithTransactions> findTopByIbanBetween(long offset,long limit);


//    List<AccountWithTransactions> findabbbb(long offset){
//        return entityManager
//    }
//
//    @Query("SELECT a FROM Account LIMIT =:limit OFFSET =:offset")
//    List<AccountWithTransactions> findAccountsInbetween(
//    @Param("LIMIT") long limit,
//    @Param("OFFSET") long offset);

    @Query(nativeQuery = true,
    value = "SELECT * FROM ACCOUNT LIMIT ?2 OFFSET ?1")
    List<AccountWithTransactions> findaccountsss(long offset, long limit);


    @Query(nativeQuery = true,
            value = "SELECT * FROM ACCOUNT WHERE OWNER_ID = ?3 LIMIT ?2 OFFSET ?1")
    List<AccountWithTransactions> findAccountsById(long offset, long limit, UUID Id);



}

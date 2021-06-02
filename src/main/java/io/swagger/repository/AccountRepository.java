package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.AllAccountsWithoutTransactions;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<AccountWithTransactions, String> {

    public AccountWithTransactions findAccountWithTransactionsByIban(String iban);

    List<AccountWithTransactions> findAllAccountsByOwner(User user);

    @Query(nativeQuery = true,
            value = "SELECT * FROM ACCOUNT WHERE OWNER_ID = ?3 LIMIT ?2 OFFSET ?1")
    List<AccountWithTransactions> findAccountsById(long offset, long limit, UUID Id);

}


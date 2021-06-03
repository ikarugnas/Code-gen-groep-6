package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.AllAccountsWithoutTransactions;
import io.swagger.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<AccountWithTransactions, String> {

    AccountWithTransactions findAccountWithTransactionsByIban(String iban);

    List<AccountWithTransactions> findAllAccountsByOwner(User user);


    @Query(value = "SELECT a FROM Account a")
    List<AccountWithTransactions> findAllAccounts(Pageable pageable);

    @Query(value = "SELECT a FROM Account a WHERE a.owner.id = ?1")
    List<AccountWithTransactions> findAccountsById(UUID Id, Pageable pageable);

}


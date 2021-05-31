package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<AccountWithTransactions, String> {

//    AccountWithTransactions
    public AccountWithTransactions findAccountWithTransactionsByIban(String iban);

    List<AccountWithTransactions> findAllAccountsByOwner(User user);



}

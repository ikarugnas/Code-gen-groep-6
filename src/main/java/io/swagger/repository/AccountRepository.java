package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountWithTransactions, String> {

//    AccountWithTransactions 

}

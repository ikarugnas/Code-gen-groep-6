package io.swagger.repository;

import io.swagger.model.Withdrawal;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.util.UUID;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    Withdrawal findByAccountFrom(String account);
}
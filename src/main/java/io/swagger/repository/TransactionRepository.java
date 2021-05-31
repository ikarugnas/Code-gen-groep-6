package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<User, UUID> {
}

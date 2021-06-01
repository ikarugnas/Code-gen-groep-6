package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsernameQuery(
            @Param("username") String username);

    User findUserByName(String name);
    User findUserByEmail(String email);
}
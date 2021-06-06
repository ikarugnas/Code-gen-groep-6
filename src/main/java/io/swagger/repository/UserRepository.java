package io.swagger.repository;

import io.swagger.model.AccountWithTransactions;
import io.swagger.model.Status;
import io.swagger.model.User;
import io.swagger.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findUserById(UUID id);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsernameQuery(
            @Param("username") String username);

    User findUserByName(String name);
    User findUserByEmail(String email);
    User findUserByRoles(UserRole role);
    User findUserByUserStatus(Status status);

    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE :username AND u.name LIKE :name AND u.email LIKE :email AND u.userStatus = :status")
    Page<User> findAllUsersWithFiltering(Pageable pageable,
                                         @Param("username") String username,
                                         @Param("name") String name,
                                         @Param("email") String email,
                                         @Param("status") Status status);


    @Query(value = "SELECT u FROM User u WHERE u.name = ?1 OR u.username = ?1 OR u.email = ?1")
    User getUserByInput(String searchString);
}


package io.swagger.repository;

import io.swagger.model.Status;
import io.swagger.model.User;
import io.swagger.model.UserRole;
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

    @Query(nativeQuery = true,
            value = "SELECT * FROM USER WHERE USERNAME = ?3 OR NAME = ?4 OR EMAIL = ?5 OR ROLE = ?6 OR USER_STATUS = ?7 LIMIT ?2 OFFSET ?1")
    List<User> findAllUsers(long offset, long limit, String username, String name, String email, UserRole role, Status status);
}
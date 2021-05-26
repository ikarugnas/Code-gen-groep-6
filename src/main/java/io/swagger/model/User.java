package io.swagger.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")

@Entity
public class User   {

  @Id
  @GeneratedValue
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("role")
  @ElementCollection(fetch = FetchType.EAGER)
  private List<UserRole> roles = null;

//  @JsonProperty("account")
//  private AccountWithTransactions account = null;

  @JsonProperty("dayLimit")
  private Double dayLimit = null;

  @JsonProperty("transactionLimit")
  private Double transactionLimit = null;

  @JsonProperty("userStatus")
  private Status userStatus = null;

  public User() {
  }

  public User(String username, String password, String name, String email, UserRole role, Double dayLimit, Double transactionLimit, Status userStatus) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.roles = Arrays.asList(role);
    this.dayLimit = dayLimit;
    this.transactionLimit = transactionLimit;
    this.userStatus = userStatus;
  }

  public User id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(example = "BG12345", required = true, description = "")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   **/
  @Schema(example = "hiIamapassword4$", required = true, description = "")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Bert Geersen", required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(example = "BertGeersen1@gmail.com", required = true, description = "")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User roles(List<UserRole> roles) {
    this.roles = roles;
    return this;
  }

  /**
   * Get role
   * @return role
   **/
  @Schema(example = "Customer", required = true, description = "")
      @NotNull

    public List<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRole> roles) {
    this.roles = roles;
  }

//  public User account(AccountWithTransactions account) {
//    this.account = account;
//    return this;
//  }

  /**
   * Get account
   * @return account
   **/
//  @Schema(description = "")
//
//    @Valid
//    public AccountWithTransactions getAccount() {
//    return account;
//  }
//
//  public void setAccount(AccountWithTransactions account) {
//    this.account = account;
//  }
//
//  public User dayLimit(Double dayLimit) {
//    this.dayLimit = dayLimit;
//    return this;
//  }

  /**
   * Get dayLimit
   * @return dayLimit
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Double getDayLimit() {
    return dayLimit;
  }

  public void setDayLimit(Double dayLimit) {
    this.dayLimit = dayLimit;
  }

  public User transactionLimit(Double transactionLimit) {
    this.transactionLimit = transactionLimit;
    return this;
  }

  /**
   * Get transactionLimit
   * @return transactionLimit
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Double getTransactionLimit() {
    return transactionLimit;
  }

  public void setTransactionLimit(Double transactionLimit) {
    this.transactionLimit = transactionLimit;
  }

  public User userStatus(Status userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  /**
   * Get userStatus
   * @return userStatus
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Status getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Status userStatus) {
    this.userStatus = userStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.roles, user.roles) &&
//        Objects.equals(this.account, user.account) &&
        Objects.equals(this.dayLimit, user.dayLimit) &&
        Objects.equals(this.transactionLimit, user.transactionLimit) &&
        Objects.equals(this.userStatus, user.userStatus);
  }

  @Override
  public int hashCode() {
//    return Objects.hash(id, username, password, name, email, roles, account, dayLimit, transactionLimit , userStatus);
    return Objects.hash(id, username, password, name, email, roles, dayLimit, transactionLimit, userStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    role: ").append(toIndentedString(roles)).append("\n");
//    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    dayLimit: ").append(toIndentedString(dayLimit)).append("\n");
    sb.append("    transactionLimit: ").append(toIndentedString(transactionLimit)).append("\n");
    sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

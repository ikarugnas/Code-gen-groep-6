package io.swagger.model;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * AccountWithTransactions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-03T11:13:57.329Z[GMT]")

@Entity(name = "Account")
public class AccountWithTransactions   {

  public AccountWithTransactions(){

  }

  public AccountWithTransactions(String iban, Double absoluteLimit, Status active, User owner, AccountType type) {
    this.iban = iban;
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;
  }

  public AccountWithTransactions(String iban, Double balance, AccountType type, User owner, Double absoluteLimit, Status active) {
    this.iban = iban;
    this.balance = balance;
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;

  }


  @Id
  @JsonProperty("iban")
  private String iban = "NL" + String.format("%01d",1) + "INHO" + String.format("%09d", 1);

  @JsonProperty("balance")
  private Double balance = 0.00;

  /**
   * Gets or Sets type
   */


  @JsonProperty("type")
  private AccountType type = AccountType.Current;

  @ManyToOne
  @JsonBackReference
  @JsonProperty("owner")
  private User owner;

  @OneToMany
  @JsonManagedReference
  @JsonProperty("transaction")
  @Valid
  private List<Transaction> transaction = new ArrayList<Transaction>();

  @JsonProperty("absoluteLimit")
  private Double absoluteLimit = 0.00;

  /**
   * Gets or Sets active
   */

  @JsonProperty("active")
  private Status active = null;

  public AccountWithTransactions iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * Get iban
   * @return iban
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", description = "")
  
    public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public AccountWithTransactions balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
   **/
  @Schema(description = "")
  
    public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public AccountWithTransactions type(AccountType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(required = true, description = "")
      @NotNull

    public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
  }

  public AccountWithTransactions owner(User owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(required = true, description = "")
      @NotNull

    public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public AccountWithTransactions transaction(List<Transaction> transaction) {
    this.transaction = transaction;
    return this;
  }

  public AccountWithTransactions addTransactionItem(Transaction transactionItem) {
    if (this.transaction == null) {
      this.transaction = new ArrayList<Transaction>();
    }
    this.transaction.add(transactionItem);
    return this;
  }

  /**
   * List of the last 50 transaction of this account
   * @return transaction
   **/
  @Schema(description = "List of the last 50 transaction of this account")
      @Valid
    public List<Transaction> getTransaction() {
    return transaction;
  }

  public void setTransaction(List<Transaction> transaction) {
    this.transaction = transaction;
  }

  public AccountWithTransactions absoluteLimit(Double absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
    return this;
  }

  /**
   * Get absoluteLimit
   * @return absoluteLimit
   **/
  @Schema(example = "0", description = "")
  
    public Double getAbsoluteLimit() {
    return absoluteLimit;
  }

  public void setAbsoluteLimit(Double absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
  }

  public AccountWithTransactions active(Status active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
   **/
  @Schema(description = "")
  
    public Status getActive() {
    return active;
  }

  public void setActive(Status active) {
    this.active = active;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountWithTransactions accountWithTransactions = (AccountWithTransactions) o;
    return Objects.equals(this.iban, accountWithTransactions.iban) &&
        Objects.equals(this.balance, accountWithTransactions.balance) &&
        Objects.equals(this.type, accountWithTransactions.type) &&
        Objects.equals(this.owner, accountWithTransactions.owner) &&
        Objects.equals(this.transaction, accountWithTransactions.transaction) &&
        Objects.equals(this.absoluteLimit, accountWithTransactions.absoluteLimit) &&
        Objects.equals(this.active, accountWithTransactions.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, balance, type, owner, absoluteLimit, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountWithTransactions {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    transaction: ").append(toIndentedString(transaction)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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

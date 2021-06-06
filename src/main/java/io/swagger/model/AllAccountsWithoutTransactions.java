package io.swagger.model;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AllAccountsWithoutTransactions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")

public class AllAccountsWithoutTransactions   {

  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("balance")
  private Double balance = null;

  @JsonProperty("currency")
  private CurrencyType currency = CurrencyType.EUR;

  public CurrencyType getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyType currency) {
    this.currency = currency;
  }

  public AllAccountsWithoutTransactions(String iban, Double balance, AccountType type, UUID owner, Double absoluteLimit, Status active, CurrencyType currency) {
    this.iban = iban;
    this.balance = balance;
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;
    this.currency = currency;
  }

  public AllAccountsWithoutTransactions(Double absoluteLimit, Status active, UUID owner, AccountType type) {
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;
  }

  public AllAccountsWithoutTransactions() {

  }

  @JsonProperty("type")
  private AccountType type = AccountType.Current;

  @JsonProperty("owner")
  private UUID owner = null;

  @JsonProperty("absoluteLimit")
  private Double absoluteLimit = null;

  @JsonProperty("active")
  private Status active = Status.Active;

  public AllAccountsWithoutTransactions iban(String iban) {
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

  public AllAccountsWithoutTransactions balance(Double balance) {
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

  public AllAccountsWithoutTransactions type(AccountType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(required = true, description = "")
      @NotNull

//    public TypeEnum getType() {
//    return type;
//  }
//
//  public void setType(TypeEnum type) {
//    this.type = type;
//  }

  public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
  }

  public AllAccountsWithoutTransactions owner(UUID owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(required = true, description = "")
      @NotNull

    public UUID getOwner() {
    return owner;
  }

  public void setOwner(UUID owner) {
    this.owner = owner;
  }

  public AllAccountsWithoutTransactions absoluteLimit(Double absoluteLimit) {
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

  public AllAccountsWithoutTransactions active(Status active) {
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
    AllAccountsWithoutTransactions allAccountsWithoutTransactions = (AllAccountsWithoutTransactions) o;
    return Objects.equals(this.iban, allAccountsWithoutTransactions.iban) &&
        Objects.equals(this.balance, allAccountsWithoutTransactions.balance) &&
        Objects.equals(this.type, allAccountsWithoutTransactions.type) &&
        Objects.equals(this.owner, allAccountsWithoutTransactions.owner) &&
        Objects.equals(this.absoluteLimit, allAccountsWithoutTransactions.absoluteLimit) &&
        Objects.equals(this.active, allAccountsWithoutTransactions.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, balance, type, owner, absoluteLimit, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllAccountsWithoutTransactions {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
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

package io.swagger.model;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import javax.persistence.*;

/**
 * Deposit
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")

@Entity
public class Deposit   {

  @Id
  @GeneratedValue
  @JsonProperty("transactionId")
  public Long id = null;

  @JsonProperty("userPerforming")
  public String userPerforming = null;

  @ManyToOne
  @JsonBackReference
  @JsonProperty("accountFrom")
  private AccountWithTransactions accountFrom = null;

  @ManyToOne
  @JsonBackReference
  @JsonProperty("accountTo")
  private AccountWithTransactions accountTo = null;

  @JsonProperty("amount")
  private Double amount = null;

  /**
   * Gets or Sets transactionType
   */
  public enum TransactionTypeEnum {
    TRANSACTION("Transaction"),
    
    WITHDRAWAL("Withdrawal"),
    
    DEPOSIT("Deposit");

    private String value;

    TransactionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TransactionTypeEnum fromValue(String text) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("transactionType")
  private TransactionTypeEnum transactionType = null;

  @JsonProperty("dateAndTime")
  private Timestamp dateAndTime = null;

  public Deposit() {
  }


  public Deposit(String userPerforming, AccountWithTransactions accountFrom, AccountWithTransactions accountTo, Double amount, String transactionType, Timestamp dateAndTime) {
    this.userPerforming = userPerforming;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.amount = amount;
    this.transactionType = Deposit.TransactionTypeEnum.fromValue(transactionType);
    this.dateAndTime = dateAndTime;
  }


  public Deposit userPerforming(String userPerforming) {
    this.userPerforming = userPerforming;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get userPerforming
   * @return userPerforming
   **/
  @Schema(example = "BG12345", required = true, description = "")
      @NotNull

//    public String getUserPerforming() {
//    return userPerforming;
//  }

  public String getUserPerforming() { return userPerforming; }

  public void setUserPerforming(String userPerforming) {
    this.userPerforming = userPerforming;
  }

  public Deposit accountFrom(AccountWithTransactions accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * Get accountFrom
   * @return accountFrom
   **/
  @Schema(description = "")
  
    public AccountWithTransactions getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(AccountWithTransactions accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Deposit accountTo(AccountWithTransactions accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * Get accountTo
   * @return accountTo
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", description = "")
  
    public AccountWithTransactions getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(AccountWithTransactions accountTo) {
    this.accountTo = accountTo;
  }

  public Deposit amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "20.23", required = true, description = "")
      @NotNull

    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Deposit transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Get transactionType
   * @return transactionType
   **/
  @Schema(example = "Deposit", required = true, description = "")
      @NotNull

    public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }

  public Deposit dateAndTime(Timestamp dateAndTime) {
    this.dateAndTime = dateAndTime;
    return this;
  }

  /**
   * Get dateAndTime
   * @return dateAndTime
   **/
  @Schema(example = "2016-08-29T09:12:33.001Z", required = true, description = "")
      @NotNull

    @Valid
    public Timestamp getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Timestamp dateAndTime) {
    this.dateAndTime = dateAndTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deposit deposit = (Deposit) o;
    return Objects.equals(this.userPerforming, deposit.userPerforming) &&
        Objects.equals(this.accountFrom, deposit.accountFrom) &&
        Objects.equals(this.accountTo, deposit.accountTo) &&
        Objects.equals(this.amount, deposit.amount) &&
        Objects.equals(this.transactionType, deposit.transactionType) &&
        Objects.equals(this.dateAndTime, deposit.dateAndTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userPerforming, accountFrom, accountTo, amount, transactionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Deposit {\n");
    
    sb.append("    userPerforming: ").append(toIndentedString(userPerforming)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    dateAndTime: ").append(toIndentedString(dateAndTime)).append("\n");
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

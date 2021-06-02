package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Withdrawal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")

@Entity
public class Withdrawal   {

  @Id
  @GeneratedValue
  @JsonProperty("withdrawalId")
  private Long id = null;

  @JsonProperty("userPerforming")
  private String userPerforming = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

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
  private OffsetDateTime dateAndTime = null;

  public Withdrawal() {
  }

  public Withdrawal(Long id, String userPerforming, String accountFrom, String accountTo, Double amount, String transactionType, OffsetDateTime dateAndTime) {
    this.id = id;
    this.userPerforming = userPerforming;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.amount = amount;
    this.transactionType = Withdrawal.TransactionTypeEnum.fromValue(transactionType);
    this.dateAndTime = dateAndTime;
  }


  public Withdrawal userPerforming(String userPerforming) {
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

    public String getUserPerforming() {
    return userPerforming;
  }

  public void setUserPerforming(String userPerforming) {
    this.userPerforming = userPerforming;
  }

  public Withdrawal accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * Get accountFrom
   * @return accountFrom
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", description = "")
  
    public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Withdrawal accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * Get accountTo
   * @return accountTo
   **/
  @Schema(description = "")
  
    public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public Withdrawal amount(Double amount) {
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

  public Withdrawal transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Get transactionType
   * @return transactionType
   **/
  @Schema(example = "Withdrawal", required = true, description = "")
      @NotNull

    public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }

  public Withdrawal dateAndTime(OffsetDateTime dateAndTime) {
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
    public OffsetDateTime getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(OffsetDateTime dateAndTime) {
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
    Withdrawal withdrawal = (Withdrawal) o;
    return Objects.equals(this.userPerforming, withdrawal.userPerforming) &&
        Objects.equals(this.accountFrom, withdrawal.accountFrom) &&
        Objects.equals(this.accountTo, withdrawal.accountTo) &&
        Objects.equals(this.amount, withdrawal.amount) &&
        Objects.equals(this.transactionType, withdrawal.transactionType) &&
        Objects.equals(this.dateAndTime, withdrawal.dateAndTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userPerforming, accountFrom, accountTo, amount, transactionType, dateAndTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Withdrawal {\n");
    
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

package io.swagger.model;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TransactionRequestBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")


public class TransactionRequestBody   {
  @JsonProperty("transactionId")
  private Long id = null;

  @JsonProperty("userPerforming")
  private String userPerforming = null;



  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("transactionType")
  private String transactionType = null;

  @JsonProperty("dateAndTime")
  private String dateAndTime = null;

  public String getDateAndTime() {
    return dateAndTime;
  }


  /**
   * Get accountFrom
   * @return accountFrom
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", required = true, description = "")
      @NotNull

  public String getUserPerforming() {
    return userPerforming;
  }

  public void setUserPerforming(String userPerforming) {
    this.userPerforming = userPerforming;
  }

    public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TransactionRequestBody amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "20", required = true, description = "")
      @NotNull

    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TransactionRequestBody accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }


  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }


  public void setDateAndTime(String dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public TransactionRequestBody accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * Get accountTo
   * @return accountTo
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", required = true, description = "")
      @NotNull

    public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionRequestBody transactionRequestBody = (TransactionRequestBody) o;
    return Objects.equals(this.accountFrom, transactionRequestBody.accountFrom) &&
        Objects.equals(this.amount, transactionRequestBody.amount) &&
        Objects.equals(this.accountTo, transactionRequestBody.accountTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountFrom, amount, accountTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionRequestBody {\n");
    
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
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

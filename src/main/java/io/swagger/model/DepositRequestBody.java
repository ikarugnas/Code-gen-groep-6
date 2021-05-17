package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DepositRequestBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T11:49:32.019Z[GMT]")


public class DepositRequestBody   {
  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("amount")
  private Double amount = null;

  public DepositRequestBody accountTo(String accountTo) {
    this.accountTo = accountTo;
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

  public DepositRequestBody amount(Double amount) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepositRequestBody depositRequestBody = (DepositRequestBody) o;
    return Objects.equals(this.accountTo, depositRequestBody.accountTo) &&
        Objects.equals(this.amount, depositRequestBody.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountTo, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepositRequestBody {\n");
    
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

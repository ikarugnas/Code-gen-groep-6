package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T11:49:32.019Z[GMT]")


public class Body   {
  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  public Body accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * Get accountFrom
   * @return accountFrom
   **/
  @Schema(example = "NL55 RABO 1234 5678 90", required = true, description = "")
      @NotNull

    public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Body amount(Long amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "20", required = true, description = "")
      @NotNull

    public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Body accountTo(String accountTo) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body body = (Body) o;
    return Objects.equals(this.accountFrom, body.accountFrom) &&
        Objects.equals(this.amount, body.amount) &&
        Objects.equals(this.accountTo, body.accountTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountFrom, amount, accountTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
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

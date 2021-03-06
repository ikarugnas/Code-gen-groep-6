package io.swagger.model;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * CreateAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-03T11:13:57.329Z[GMT]")


public class CreateAccount   {
  /**
   * Gets or Sets type
   */

  @JsonProperty("type")
  private String type = "Current";

  @JsonProperty("owner")
  private String owner;

  @JsonProperty("absoluteLimit")
  private Double absoluteLimit = 0.00;

  /**
   * Gets or Sets active
   */

  @JsonProperty("active")
  private Status active = Status.Active;

  @JsonProperty("currency")
  private CurrencyType currency = CurrencyType.EUR;

  public CurrencyType getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyType currency) {
    this.currency = CurrencyType.EUR;
  }

  public CreateAccount type(String type) {
    this.type = type;
    return this;
  }

  public CreateAccount(Double absoluteLimit, Status active, String owner, String type, CurrencyType currency) {
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;
    this.currency = CurrencyType.EUR;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CreateAccount owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(required = false, description = "")

    public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public CreateAccount absoluteLimit(Double absoluteLimit) {
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

  public CreateAccount active(Status active) {
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
    CreateAccount createAccount = (CreateAccount) o;
    return Objects.equals(this.type, createAccount.type) &&
        Objects.equals(this.owner, createAccount.owner) &&
        Objects.equals(this.absoluteLimit, createAccount.absoluteLimit) &&
        Objects.equals(this.active, createAccount.active)&&
        Objects.equals(this.currency, createAccount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, owner, absoluteLimit, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAccount {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

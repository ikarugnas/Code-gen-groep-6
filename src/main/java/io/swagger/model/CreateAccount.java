package io.swagger.model;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")


public class CreateAccount   {
  /**
   * Gets or Sets type
   */

  @JsonProperty("type")
  private AccountType type = AccountType.Current;

  @JsonProperty("owner")
  private String owner;

  @JsonProperty("absoluteLimit")
  private Double absoluteLimit = 0.00;

  /**
   * Gets or Sets active
   */

  @JsonProperty("active")
  private UserStatus active = UserStatus.Active;

  public CreateAccount type(AccountType type) {
    this.type = type;
    return this;
  }

  public CreateAccount(Double absoluteLimit, UserStatus active, String owner, AccountType type) {
    this.type = type;
    this.owner = owner;
    this.absoluteLimit = absoluteLimit;
    this.active = active;
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

  public CreateAccount owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(required = true, description = "")
      @NotNull

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

  public CreateAccount active(UserStatus active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
   **/
  @Schema(description = "")
  
    public UserStatus getActive() {
    return active;
  }

  public void setActive(UserStatus active) {
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
        Objects.equals(this.active, createAccount.active);
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

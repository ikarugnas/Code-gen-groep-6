package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegisterDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")


public class RegisterDTO   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * If no role is given default will be Customer.
   */
  @JsonProperty("role")
  private UserRole role = UserRole.ROLE_Customer;

  @JsonProperty("dayLimit")
  private Double dayLimit = null;

  @JsonProperty("transactionLimit")
  private Double transactionLimit = null;

  /**
   * Gets or Sets userStatus
   */
  public enum UserStatusEnum {
    ACTIVE("Active"),
    
    INACTIVE("Inactive");

    private String value;

    UserStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UserStatusEnum fromValue(String text) {
      for (UserStatusEnum b : UserStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("userStatus")
  private UserStatusEnum userStatus = UserStatusEnum.ACTIVE;

  public RegisterDTO username(String username) {
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

  public RegisterDTO password(String password) {
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

  public RegisterDTO name(String name) {
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

  public RegisterDTO email(String email) {
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

  public RegisterDTO role(UserRole role) {
    this.role = role;
    return this;
  }

  /**
   * If no role is given default will be Customer.
   * @return role
   **/
  @Schema(description = "If no role is given default will be Customer.")
  
    public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public RegisterDTO dayLimit(Double dayLimit) {
    this.dayLimit = dayLimit;
    return this;
  }

  /**
   * If not given default will be used.
   * @return dayLimit
   **/
  @Schema(description = "If not given default will be used.")
  
    public Double getDayLimit() {
    return dayLimit;
  }

  public void setDayLimit(Double dayLimit) {
    this.dayLimit = dayLimit;
  }

  public RegisterDTO transactionLimit(Double transactionLimit) {
    this.transactionLimit = transactionLimit;
    return this;
  }

  /**
   * If not given default will be used.
   * @return transactionLimit
   **/
  @Schema(description = "If not given default will be used.")
  
    public Double getTransactionLimit() {
    return transactionLimit;
  }

  public void setTransactionLimit(Double transactionLimit) {
    this.transactionLimit = transactionLimit;
  }

  public RegisterDTO userStatus(UserStatusEnum userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  /**
   * Get userStatus
   * @return userStatus
   **/
  @Schema(description = "")
  
    public UserStatusEnum getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(UserStatusEnum userStatus) {
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
    RegisterDTO registerDTO = (RegisterDTO) o;
    return Objects.equals(this.username, registerDTO.username) &&
        Objects.equals(this.password, registerDTO.password) &&
        Objects.equals(this.name, registerDTO.name) &&
        Objects.equals(this.email, registerDTO.email) &&
        Objects.equals(this.role, registerDTO.role) &&
        Objects.equals(this.dayLimit, registerDTO.dayLimit) &&
        Objects.equals(this.transactionLimit, registerDTO.transactionLimit) &&
        Objects.equals(this.userStatus, registerDTO.userStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, name, email, role, dayLimit, transactionLimit, userStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterDTO {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

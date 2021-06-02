package io.swagger.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

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
  private Double dayLimit = 1000.00;

  @JsonProperty("transactionLimit")
  private Double transactionLimit = 500.00;

  @JsonProperty("userStatus")
  private Status userStatus = Status.Active;

  public RegisterDTO() {
  }

  public RegisterDTO(String username, String password, String name, String email) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
  }

  public RegisterDTO(String username, String password, String name, String email, UserRole role) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.role = role;
  }

  public RegisterDTO username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(example = "BG12345", required = true, description = "")

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

  public RegisterDTO userStatus(Status userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  /**
   * Get userStatus
   * @return userStatus
   **/
  @Schema(description = "")
  
    public Status getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Status userStatus) {
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

  /**
   * Looks for an empty property in this object
   * returns what is empty/null or returns null
   */
  public String getNullOrEmptyProperties(){
    List<String> emptyProperties = new ArrayList<String>();

    // Empty properties are added to the list
    if (this.username == null || this.username.isEmpty()){
      emptyProperties.add("username");
    }
    if (this.password == null || this.password.isEmpty()){
      emptyProperties.add("password");
    }
    if (this.name == null || this.name.isEmpty()){
      emptyProperties.add("name");
    }
    if (this.email == null || this.email.isEmpty()){
      emptyProperties.add("email");
    }

    int totalEmptyProperties = emptyProperties.size();

    if (totalEmptyProperties > 0) {
      // Get the first empty property and uppercase the first letter
      StringBuilder emptyString = new StringBuilder();
      emptyString.append(StringUtils.capitalize(emptyProperties.get(0)));
      emptyProperties.remove(0);

      // Add all properties to the string
      if (emptyProperties.size() >= 1) {
        while (emptyProperties.size() > 1) {
          emptyString.append(", ").append(emptyProperties.get(0));
          emptyProperties.remove(0);
        }
        emptyString.append(" and ").append(emptyProperties.get(0));
      }

      if (totalEmptyProperties > 1){
        emptyString.append(" are empty");
      } else {
        emptyString.append(" is empty");
      }

      return emptyString.toString();
    }

    return null;
  }

  public boolean hasValidEmail() {
    // Checks if email has an @
    if (!this.email.matches("(.+)(\\@)(.+)")) { return false; }

    String[] splitEmail = this.email.split("@");
    String prefix = splitEmail[0];
    String domain = splitEmail[1];

    // Checks if prefix is valid
    if (!(prefix.matches("[a-zA-Z0-9]+") || prefix.matches("([a-zA-Z0-9]+)([\\-\\.\\_])([a-zA-Z0-9]+)"))) { return false; }

    // Checks if domain is valid
    if (!(domain.matches("([a-zA-Z0-9]+)([\\.])([a-z]{2,})") || domain.matches("([a-zA-Z0-9]+)(\\-)([a-zA-Z0-9]+)([\\.])([a-z]{2,})"))) { return false; }

    return true;
  }

  public String validatePassword() {

    List<String> errors = new ArrayList<String>();

    // Check if password length is 8 or more
    if (this.password.length() < 8) {
      errors.add("password length must be 8 or more");
    }

    // Check if password has a letter
    if (!(this.password.matches("(.*)([a-z])(.*)"))) {
      errors.add("password misses a letter");
    }

    // Check if password has a capital letter
    if (!(this.password.matches("(.*)([A-Z])(.*)"))) {
      errors.add("password misses a capital letter");
    }

    // Check if password has a number
    if (!(this.password.matches("(.*)([0-9])(.*)"))) {
      errors.add("password misses a number");
    }

    // Check if password has one of these special characters (!, @, #, $, %, ^, & or *)
    if (!(this.password.matches("(.*)([\\!\\@\\#\\$\\%\\^\\&\\*])(.*)"))) {
      errors.add("password misses one of these special characters [!, @, #, $, %, ^, & or *]");
    }

    if (errors.size() > 0) {
      StringBuilder sb = new StringBuilder();

      sb.append(StringUtils.capitalize(errors.get(0)));
      errors.remove(0);

      while (errors.size() > 1){
        sb.append(", ").append(errors.get(0));
        errors.remove(0);
      }

      if (errors.size() > 0){
        sb.append(" and ").append(errors.get(0));
      }

      return sb.toString();
    }

    // returns null if password is valid
    return null;
  }
}

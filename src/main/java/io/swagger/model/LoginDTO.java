package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoginDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-19T08:27:21.236Z[GMT]")


public class LoginDTO   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  public LoginDTO username(String username) {
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

  public LoginDTO password(String password) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginDTO loginDTO = (LoginDTO) o;
    return Objects.equals(this.username, loginDTO.username) &&
        Objects.equals(this.password, loginDTO.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginDTO {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
    StringBuilder emptyProperties = new StringBuilder();
    int total = 0;

    if (this.username == null || this.username.isEmpty()){
      emptyProperties.append("Username");
      total++;
    }
    if (this.password == null || this.password.isEmpty()){
      if (total >= 1){
        emptyProperties.append(" and password");
      } else { emptyProperties.append("Password"); }
      total++;
    }

    if (total > 0) {
      if (total > 1) {
        emptyProperties.append(" are empty");
      } else {
        emptyProperties.append(" is empty");
      }

      return emptyProperties.toString();
    }
    return null;
  }

}

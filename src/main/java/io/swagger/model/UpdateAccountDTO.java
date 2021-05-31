package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAccountDTO {

    @JsonProperty("status")
    public UserStatus status = UserStatus.Active;

    @JsonProperty("absoluteLimit")
    public double absoluteLimit = 00.00;

    public UpdateAccountDTO(UserStatus status, double absoluteLimit) {
        this.status = status;
        this.absoluteLimit = absoluteLimit;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }
}

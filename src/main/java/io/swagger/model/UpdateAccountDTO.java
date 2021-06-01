package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAccountDTO {

    @JsonProperty("status")
    public Status status = Status.Active;

    @JsonProperty("absoluteLimit")
    public double absoluteLimit = 00.00;

    public UpdateAccountDTO(Status status, double absoluteLimit) {
        this.status = status;
        this.absoluteLimit = absoluteLimit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }
}

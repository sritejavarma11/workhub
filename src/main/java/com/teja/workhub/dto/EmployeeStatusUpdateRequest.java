package com.teja.workhub.dto;

import com.teja.workhub.entity.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeStatusUpdateRequest {

    @NotNull
    private EmployeeStatus status;

    public EmployeeStatus getStatus() {
        return status;
    }
}

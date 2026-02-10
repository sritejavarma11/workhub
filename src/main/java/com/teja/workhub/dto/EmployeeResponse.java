package com.teja.workhub.dto;

import com.teja.workhub.entity.Employee;
import com.teja.workhub.entity.EmployeeStatus;

public class EmployeeResponse {

    private int id;
    private String name;
    private String email;
    private EmployeeStatus status;

    public EmployeeResponse(int id, String name, EmployeeStatus status, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeStatus getStatus() {return status;}
}

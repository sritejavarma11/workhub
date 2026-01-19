package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> getAllEmployees();
}

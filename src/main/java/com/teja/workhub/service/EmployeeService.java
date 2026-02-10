package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.dto.EmployeeStatusUpdateRequest;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.entity.EmployeeStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    Page<EmployeeResponse> getAllEmployees(String name, String email, EmployeeStatus status, LocalDate from, LocalDate to, Pageable pageable);

    EmployeeResponse getEmployeeById(int id);

    EmployeeResponse updateEmployee(int id, @RequestBody EmployeeRequest employeeRequest);

    EmployeeResponse updateEmployeeStatus(int id, EmployeeStatus status);

    void deleteEmployee(int id);
}

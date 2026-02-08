package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    Page<EmployeeResponse> getAllEmployees(String name, String email, Pageable pageable);

    EmployeeResponse getEmployeeById(int id);

    EmployeeResponse updateEmployee(int id, @RequestBody EmployeeRequest employeeRequest);

    void deleteEmployee(int id);
}

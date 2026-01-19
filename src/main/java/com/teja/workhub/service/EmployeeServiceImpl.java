package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getEmail());
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeeResponse(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail()
                ))
                .toList();
    }
}

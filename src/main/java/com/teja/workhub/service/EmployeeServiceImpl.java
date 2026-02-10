package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.entity.EmployeeStatus;
import com.teja.workhub.exception.EmployeeNotFoundException;
import com.teja.workhub.repository.EmployeeRepository;
import com.teja.workhub.specifications.EmployeeSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmail());
        employee.setStatus(EmployeeStatus.ACTIVE);

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getStatus(), savedEmployee.getEmail());
    }

    @Override
    public Page<EmployeeResponse> getAllEmployees(String name, String email, EmployeeStatus status, LocalDate from , LocalDate to, Pageable pageable) {
        Page<Employee> employeePage;

        Specification<Employee> spec =
                Specification.where(EmployeeSpecifications.hasName(name))
                        .and(EmployeeSpecifications.hasEmail(email))
                        .and(EmployeeSpecifications.hasStatus(status))
                        .and(EmployeeSpecifications.createdAfter(from))
                        .and(EmployeeSpecifications.createdBefore(to));


        employeePage = employeeRepository.findAll(spec, pageable);

        Page<EmployeeResponse> employeeResponsePage = employeePage.map(
                emp -> new EmployeeResponse(emp.getId(), emp.getName(), emp.getStatus(), emp.getEmail())

        );

        return employeeResponsePage;

    }

    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getStatus(), employee.getEmail());
    }

    @Override
    public EmployeeResponse updateEmployee(int id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        if(employeeRequest.getName() != null) {
            employee.setName(employeeRequest.getName());
        }
        if(employeeRequest.getEmail() != null) {
            employee.setEmail(employeeRequest.getEmail());
        }

        Employee updatedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(updatedEmployee.getId(), updatedEmployee.getName(), updatedEmployee.getStatus(), updatedEmployee.getEmail());
    }

    public EmployeeResponse updateEmployeeStatus(int id, EmployeeStatus status) {
        Employee employee  = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        employee.setStatus(status);

        Employee updatedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(updatedEmployee.getId(), updatedEmployee.getName(), updatedEmployee.getStatus(), updatedEmployee.getEmail());

    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        employeeRepository.delete(employee);
    }
}

package com.teja.workhub.service;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.exception.EmployeeNotFoundException;
import com.teja.workhub.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail());
    }

    @Override
    public EmployeeResponse updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(updatedEmployee.getId(), updatedEmployee.getName(), updatedEmployee.getEmail());
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        employeeRepository.delete(employee);
    }
}

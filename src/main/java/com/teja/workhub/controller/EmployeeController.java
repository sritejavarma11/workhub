package com.teja.workhub.controller;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.repository.EmployeeRepository;
import com.teja.workhub.service.EmployeeService;
import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.createEmployee(employeeRequest);
    }

    @GetMapping
    public List<EmployeeResponse> findAllEmployees(){
        return employeeService.getAllEmployees();
    }
}

package com.teja.workhub.controller;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.dto.EmployeeStatusUpdateRequest;
import com.teja.workhub.dto.PaginatedResponse;
import com.teja.workhub.entity.EmployeeStatus;
import com.teja.workhub.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse response = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<EmployeeResponse>> findAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) EmployeeStatus status,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate to){

            Sort sort = direction.equalsIgnoreCase("asc")
                            ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();

            Pageable pageable  = PageRequest.of(page, size, sort);

            Page<EmployeeResponse> pageresponse = employeeService.getAllEmployees(name, email, status, from, to, pageable);

            PaginatedResponse<EmployeeResponse> response = new PaginatedResponse<>(
                    pageresponse.getContent(),
                    pageresponse.getNumber(),
                    pageresponse.getSize(),
                    (int)pageresponse.getTotalElements(),
                    pageresponse.getTotalPages(),
                    pageresponse.isLast()
            );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeBId(@PathVariable int id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeResponse> updateEmployeeStatus(@PathVariable int id, @Valid @RequestBody EmployeeStatusUpdateRequest employeeStatus){

        EmployeeResponse employeeResponse = employeeService.updateEmployeeStatus(id, employeeStatus.getStatus());

        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

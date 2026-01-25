package com.teja.workhub.controller;

import com.teja.workhub.dto.EmployeeRequest;
import com.teja.workhub.dto.EmployeeResponse;
import com.teja.workhub.dto.PaginatedResponse;
import com.teja.workhub.entity.Employee;
import com.teja.workhub.repository.EmployeeRepository;
import com.teja.workhub.service.EmployeeService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
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
            @RequestParam(defaultValue = "asc") String direction){

            Sort sort = direction.equalsIgnoreCase("asc")
                            ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();

            Pageable pageable  = PageRequest.of(page, size, sort);

            Page<EmployeeResponse> pageresponse = employeeService.getAllEmployees(pageable);

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

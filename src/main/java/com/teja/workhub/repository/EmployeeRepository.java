package com.teja.workhub.repository;

import com.teja.workhub.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Page<Employee> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Employee> findByEmailContainingIgnoreCase(
            String email,
            Pageable pageable
    );

    Page<Employee> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable
    );
}

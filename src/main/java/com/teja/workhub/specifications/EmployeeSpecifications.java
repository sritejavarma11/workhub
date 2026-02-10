package com.teja.workhub.specifications;

import com.teja.workhub.entity.Employee;
import com.teja.workhub.entity.EmployeeStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeSpecifications{

    private EmployeeSpecifications() {
        // utility class
    }

    public static Specification<Employee> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.isBlank())
                        ? cb.conjunction()
                        : cb.like(
                        cb.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, cb) ->
                (email == null || email.isBlank())
                        ? cb.conjunction()
                        : cb.like(
                        cb.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> hasStatus(EmployeeStatus status) {
        return (root, query, cb) ->
                (status == null)
                        ? cb.conjunction()
                        : cb.equal(root.get("status"), status);
    }

    public static Specification<Employee> createdAfter(LocalDate from) {
        return (root, query, cb) ->
                (from == null)
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(
                                root.get("createdAt"), from.atStartOfDay());
    }

    public static Specification<Employee> createdBefore(LocalDate to) {
        return (root, query, cb) ->
                (to == null)
                        ? cb.conjunction()
                        : cb.lessThan(
                                root.get("createdAt"), to.plusDays(1).atStartOfDay());
    }

}

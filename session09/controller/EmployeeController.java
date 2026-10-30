package com.example.session09.controller;

import com.example.session09.dto.ApiResponse;
import com.example.session09.dto.EmployeeCreateDTO;
import com.example.session09.entity.Employee;
import com.example.session09.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO dto) {

        Employee savedEmployee = employeeService.createEmployee(dto);

        ApiResponse<Employee> response = new ApiResponse<>(
                "SUCCESS",
                "Thêm nhân viên thành công",
                savedEmployee
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
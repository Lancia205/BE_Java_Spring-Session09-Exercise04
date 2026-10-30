package com.example.session09.controller;

import com.example.session09.dto.ApiResponse;
import com.example.session09.dto.DepartmentDTO;
import com.example.session09.entity.Department;
import com.example.session09.repository.DepartmentRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {

        Department department = new Department();

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        Department savedDepartment = departmentRepository.save(department);

        ApiResponse<Department> response = new ApiResponse<>(
                "SUCCESS",
                "Tạo phòng ban thành công",
                savedDepartment
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
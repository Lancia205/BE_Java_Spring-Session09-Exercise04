package com.example.session09.service;

import com.example.session09.dto.EmployeeCreateDTO;
import com.example.session09.entity.Department;
import com.example.session09.entity.Employee;
import com.example.session09.exception.DuplicateResourceException;
import com.example.session09.exception.ResourceNotFoundException;
import com.example.session09.repository.DepartmentRepository;
import com.example.session09.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Employee createEmployee(EmployeeCreateDTO dto) {

        // Kiểm tra phòng ban có tồn tại không
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Phòng ban không tồn tại"));

        // Kiểm tra email có bị trùng không
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã được sử dụng");
        }

        // Tạo nhân viên mới
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }
}
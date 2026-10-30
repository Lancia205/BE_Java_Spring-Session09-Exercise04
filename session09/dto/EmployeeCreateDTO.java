package com.example.session09.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeCreateDTO {

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(03|05|07|08|09)[0-9]{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @Min(value = 5000000, message = "Lương tối thiểu là 5.000.000")
    private Long salary;

    @NotNull(message = "Phòng ban không được để trống")
    private Long departmentId;
}
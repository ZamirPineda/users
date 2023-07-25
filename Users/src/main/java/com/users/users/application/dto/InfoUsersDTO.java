package com.users.users.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InfoUsersDTO {
    private int id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String birthday;
    @NotNull
    @NotBlank
    @NotEmpty
    private String dni;
}

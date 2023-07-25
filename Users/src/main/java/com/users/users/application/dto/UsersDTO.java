package com.users.users.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {
    private int id;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotNull(message = "mail cannot be null")
    @NotBlank(message = "mail cannot be blank")
    @NotEmpty(message = "mail cannot be empty")
    private String mail;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @Valid
    private InfoUsersDTO infoUsersDTO;
}

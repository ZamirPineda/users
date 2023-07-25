package com.users.users.application.dto;

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
    private String name;
    private String username;
    private String mail;
    private String password;
    @Valid
    private InfoUsersDTO infoUsersDTO;
}

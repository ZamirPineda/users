package com.users.users.application.dto;

import com.users.users.infraestructure.persistence.model.InfoUsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private InfoUsersDTO infoUsersDTO;
}

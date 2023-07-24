package com.users.users.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String dni;
    private String password;
}

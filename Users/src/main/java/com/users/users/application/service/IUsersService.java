package com.users.users.application.service;

import com.users.users.application.dto.ResponseDTO;
import com.users.users.application.dto.UsersDTO;

public interface IUsersService {
    ResponseDTO<?> getUserByDNI(String dni);

    ResponseDTO<?> createUser(UsersDTO usersDTO);
}

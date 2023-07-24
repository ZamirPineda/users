package com.users.users.infraestructure.persistence.mapper;

import com.users.users.application.dto.InfoUsersDTO;
import com.users.users.application.dto.UsersDTO;
import com.users.users.infraestructure.persistence.model.UsersEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUsersMapper {
    @BeanMapping(qualifiedByName = "FromUsersDto")
    UsersEntity fromUsersDto(UsersDTO usersDTO);

    @Mapping(source = "infoUsersEntity", target = "infoUsersDTO")
    UsersDTO toUsersDto(UsersEntity usersEntity); // El atributo source hace referencia al objeto de entrada (origen) y el atributo target hace referencia al objeto de salida (destino).
    InfoUsersDTO toInfoUsersDto(InfoUsersDTO infoUsersDTO);
}

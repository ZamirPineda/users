package com.users.users.application.service;

import com.users.users.application.dto.ResponseDTO;
import com.users.users.application.dto.UsersDTO;
import com.users.users.infraestructure.persistence.mapper.IUsersMapper;
import com.users.users.infraestructure.persistence.model.InfoUsersEntity;
import com.users.users.infraestructure.persistence.model.UsersEntity;
import com.users.users.infraestructure.persistence.repository.IRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImpl implements IUsersService {
    @Autowired
    IRepository repository;
    @Autowired
    IUsersMapper usersMapper;

    @Override
    public ResponseDTO<UsersDTO> getUserByDNI(String dni) {
        ResponseDTO<UsersDTO> responseDto = new ResponseDTO<>();
        Optional<UsersEntity> usersEntityOptional = repository.findUsersEntitiesByInfoUsersEntity_Dni(dni);
        log.info("Response DB getByDNI {}", usersEntityOptional);
        usersEntityOptional.map(usersMapper::toUsersDto).ifPresent(responseDto::setData);
        /***
         if (usersEntityOptional.isPresent()) {
         UsersEntity usersEntity = usersEntityOptional.get();
         responseDto.setData(usersMapper.toUsersDto(usersEntity));
         }*/
        return responseDto;
    }

    @Override
    public ResponseDTO<UsersDTO> createUser(UsersDTO user) {
        log.info("POST objeto de entrada: {}", user);
        ResponseDTO<UsersDTO> responseDto = new ResponseDTO<>();
        try {
            UsersEntity usersEntity = new UsersEntity();
            InfoUsersEntity infoUsersEntity = new InfoUsersEntity();
            usersEntity.setUsername(user.getUsername());
            usersEntity.setName(user.getName());
            usersEntity.setMail(user.getMail());
            usersEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            infoUsersEntity.setBirthday(user.getInfoUsersDTO().getBirthday());
            infoUsersEntity.setDni(user.getInfoUsersDTO().getDni());
            usersEntity.setInfoUsersEntity(infoUsersEntity);
            repository.save(usersEntity);
            log.info("Save DB createUser {}", usersEntity);
        } catch (Exception e) {
            responseDto.setCode("01");
            responseDto.setMessage("Ningun item coincide con el parametro ingresado");
        }
        return responseDto;
    }
}

package com.users.users.infraestructure.persistence.repository;

import com.users.users.infraestructure.persistence.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findUsersEntitiesByInfoUsersEntity_Dni(String dni);

    Optional<UsersEntity> findByInfoUsersEntity_Dni(String dni);

    Optional<UsersEntity> findByUsername(String user);
    boolean existsByMail(String mail);

    boolean existsUsersEntityByInfoUsersEntity_Dni(String dni);

}

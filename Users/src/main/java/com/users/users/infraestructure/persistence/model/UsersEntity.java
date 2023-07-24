package com.users.users.infraestructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.users.users.infraestructure.persistence.model.InfoUsersEntity;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String mail;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="informacion_usuario_id", referencedColumnName = "id")
    private InfoUsersEntity infoUsersEntity;

}

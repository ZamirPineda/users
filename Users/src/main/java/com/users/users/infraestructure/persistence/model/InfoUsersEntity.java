package com.users.users.infraestructure.persistence.model;

import lombok.Data;

import jakarta.persistence.*;
@Data
@Entity
public class InfoUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String birthday;
    private String dni;
}

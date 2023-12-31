package com.users.users.application.controller;

import com.users.users.application.dto.ResponseDTO;
import com.users.users.application.dto.UsersDTO;
import com.users.users.application.service.IUsersService;
import com.users.users.infraestructure.persistence.model.InfoUsersEntity;
import com.users.users.infraestructure.persistence.model.UsersEntity;
import com.users.users.infraestructure.persistence.repository.IRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping("/app/users")
public class UsersController {
    @Autowired
    IRepository repository;

    @Autowired
    IUsersService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody UsersDTO user) {
        log.info("Aqui inicia el post{}", user);
        // Validar si el correo electrónico ya existe en la base de datos
        if (repository.existsByMail(user.getMail())) {
            // Correo electrónico ya existe en la base de datos, devolver conflicto (409)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Validar si el número de identificación (dni) ya existe en la base de datos
        if (repository.existsUsersEntityByInfoUsersEntity_Dni(user.getInfoUsersDTO().getDni())) {
            // DNI ya existe en la base de datos, devolver conflicto (409)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Si el correo electrónico y dni, no existe, proceder con la creación del usuario
        //ResponseDTO<?> usersEntity = service.createUser(user);
        /***
        UsersEntity usersEntity = new UsersEntity();
        InfoUsersEntity infoUsersEntity = new InfoUsersEntity();
        usersEntity.setUsername(user.getUsername());
        usersEntity.setName(user.getName());
        usersEntity.setMail(user.getMail());
        usersEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        infoUsersEntity.setBirthday(user.getInfoUsersEntity().getBirthday());
        infoUsersEntity.setDni(user.getInfoUsersEntity().getDni());
        usersEntity.setInfoUsersEntity(infoUsersEntity);
        log.info("POST objeto en BD: {}", usersEntity);
        repository.save(usersEntity);
         */
        //return ResponseEntity.status(HttpStatus.CREATED).body(usersEntity);
        return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UsersEntity>> readAll() {
        List<UsersEntity> users = repository.findAll();
        if (users.isEmpty()) {
            // No hay registros en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        } else {
            // Responder con la lista de registros y el código de estado 200 (OK)
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/dni")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> readByDNI(@RequestParam String dni) {
        ResponseDTO<?> user = service.getUserByDNI(dni);
        log.info("GET readByDNI:{}", user);
        if (user == null) {
            // No hay registros en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        } else {
            // Responder con la lista de registros y el código de estado 200 (OK)
            return new ResponseEntity<>(service.getUserByDNI(dni), HttpStatus.OK);
        }
    }

    @PutMapping("/{user}")
    public ResponseEntity<UsersEntity> updatePassword(@PathVariable String user, @Validated @RequestBody UsersEntity body) {
        // Buscar el usuario en la base de datos por el nombre de usuario
        Optional<UsersEntity> userInBD = repository.findByUsername(user);

        if (userInBD.isPresent()) {
            // El registro existe, proceder con la actualización del campo de contraseña
            UsersEntity existingUser = userInBD.get();
            existingUser.setPassword(body.getPassword());
            repository.save(existingUser);

            // Responder con el objeto actualizado y el código de estado 200 (OK)
            return ResponseEntity.ok(existingUser);
        } else {
            // El registro no se encontró en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByDNI(@RequestParam String dni) {
        // Buscar el usuario por el DNI en la base de datos
        Optional<UsersEntity> userInBD = repository.findByInfoUsersEntity_Dni(dni);

        if (userInBD.isPresent()) {
            // El registro existe, proceder con la eliminación
            repository.delete(userInBD.get());
            // Responder con 204 (NO_CONTENT)
            return ResponseEntity.noContent().build();
        } else {
            // El registro no se encontró en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll(){
        //Metodo exclusivo para eliminar información obsoleta en general
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }


    /***
     *
     * @GetMapping("/dni")
     *     @ResponseStatus(HttpStatus.OK)
     *     public ResponseEntity<UsersEntity> readByDNI(@RequestParam String dni) {
     *         Optional<UsersEntity> user = repository.findUsersEntitiesByInfoUsersEntity_Dni(dni);
     *         if (user.isEmpty()) {
     *             // No hay registros en la base de datos, responder con 404 (NOT_FOUND)
     *             return ResponseEntity.notFound().build();
     *         } else {
     *             // Responder con la lista de registros y el código de estado 200 (OK)
     *             return ResponseEntity.ok(user.get());
     *         }
     *     }
     */
}

package com.users.users.security;

import com.users.users.infraestructure.persistence.model.UsersEntity;
import com.users.users.infraestructure.persistence.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IRepository repo;
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        UsersEntity usersEntity = repo.findUsersEntitiesByInfoUsersEntity_Dni(dni).orElseThrow(() -> new UsernameNotFoundException("El usuario con número de identificación " + dni + " no existe."));
        return new UserDetailsImpl(usersEntity);
    }
}

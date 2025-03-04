package com.devjr.apiJWT.services;

import com.devjr.apiJWT.models.UserEntity;
import com.devjr.apiJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username);

        if(userEntity==null){
            throw new RuntimeException("usuario no  encontrado");
        }

        Collection<SimpleGrantedAuthority> authorities=userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}

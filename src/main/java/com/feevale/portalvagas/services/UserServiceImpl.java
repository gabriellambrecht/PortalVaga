package com.feevale.portalvagas.services;

import com.feevale.portalvagas.entities.Usuario;
import com.feevale.portalvagas.repositories.RoleRepository;
import com.feevale.portalvagas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario user) {
        user.setPapeis(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
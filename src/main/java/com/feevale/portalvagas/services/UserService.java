package com.feevale.portalvagas.services;

import com.feevale.portalvagas.entities.Usuario;

public interface UserService {
    void save(Usuario user);

    Usuario findByUsername(String username);
}
package com.feevale.portalvagas.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
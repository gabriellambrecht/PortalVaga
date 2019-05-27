package com.feevale.portalvagas.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String senha;

    @Transient
    private String senhaConfirmada;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getsenhaConfirmada() {
        return senhaConfirmada;
    }

    public void setsenhaConfirmada(String senhaConfirmada) {
        this.senhaConfirmada = senhaConfirmada;
    }

    
}

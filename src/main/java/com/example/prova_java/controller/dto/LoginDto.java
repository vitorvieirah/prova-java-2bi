package com.example.prova_java.controller.dto;

public class LoginDto {
    private String email;
    private String senha;
    private String token;

    public LoginDto(String email, String senha, String token) {
        this.email = email;
        this.senha = senha;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.DTO;

public abstract class Usuario {
    protected String nome;
    protected String login;
    protected String senha;
    
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}
package com.Symple.Door.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "credenciais")
public class CredenciaisE implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredencial;

    private String nome;
    private String senha;

    public CredenciaisE() {
    }

    public CredenciaisE(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Long getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(Long idCredencial) {
        this.idCredencial = idCredencial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "CredenciaisE{" +
                "idCredencial=" + idCredencial +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}


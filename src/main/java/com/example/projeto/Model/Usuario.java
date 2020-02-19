package com.example.projeto.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "client")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name",columnDefinition = "VARCHAR(40)")
    private String name;

    @Column(name="email",columnDefinition = "VARCHAR(40)")
    private String email;

    @Column(name="senha",columnDefinition = "VARCHAR(100)")
    private String senha;

    private int active = 1;


    public Usuario(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
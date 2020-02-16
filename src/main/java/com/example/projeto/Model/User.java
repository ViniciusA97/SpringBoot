package com.example.projeto.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Usuario")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name",columnDefinition = "VARCHAR(40)")
    private String name;
    @Column(name="email",columnDefinition = "VARCHAR(40)")
    private String email;
    @Column(name="senha",columnDefinition = "VARCHAR(40)")
    private String senha;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.senha = password;
    }

    public User(){}

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
}

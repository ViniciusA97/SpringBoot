package com.example.projeto.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Usuario")
public class User implements Serializable {

    private static final long serialVersionUID= 1L;

    @Column(name="idUsuario", columnDefinition = "INT")
    @Id
    @GeneratedValue
    private long idUsuario;

    public long getId() {
        return idUsuario;
    }

    public void setId(int idUser) {
        this.idUsuario = idUser;
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

    public long getIdUser() {
        return idUsuario;
    }

    public void setIdUser(long idUser) {
        this.idUsuario = idUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return senha;
    }

    public void setPassword(String password) {
        this.senha = password;
    }
}

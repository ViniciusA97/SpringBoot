package com.example.projeto.Repository;

import com.example.projeto.Model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<Usuario,Long> {
    Usuario findByEmail(String email);
    void deleteByEmail(String email);
    List<Usuario> findAll();

}

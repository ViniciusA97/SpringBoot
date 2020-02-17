package com.example.projeto.Repository;

import com.example.projeto.Model.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Usuario,Long> {
    Usuario findByEmail(String email);

}

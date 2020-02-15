package com.example.projeto.Repository;

import com.example.projeto.Model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);

}

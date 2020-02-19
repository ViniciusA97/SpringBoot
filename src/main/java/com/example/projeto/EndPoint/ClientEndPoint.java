package com.example.projeto.EndPoint;
import com.example.projeto.Error.CustomErrorType;
import com.example.projeto.Model.Usuario;
import com.example.projeto.Repository.UserRepository;
import com.example.projeto.SecurityConfig.MailSenderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ErrorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("client")
public class ClientEndPoint {

    @Autowired
    Environment environment;
    @Autowired
      UserRepository userDAO;
    @Autowired
    MailSenderTest mailSender;


    //READ
    @GetMapping(path="/getAll")
    public ResponseEntity<?> getListClient(){


        List<Usuario> user = this.userDAO.findAll();
        return new ResponseEntity<>(this.userDAO.findAll() , HttpStatus.OK);

    }

    //CREATE OK
    @PostMapping(path ="/create")
    public ResponseEntity<?> createUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("senha") String senha){
        Usuario test = userDAO.findByEmail(email);
        if(!(test==null)) return new ResponseEntity<>("Email ja cadastrado", HttpStatus.NOT_ACCEPTABLE);
        Usuario user = new Usuario(name,email,senha);
        userDAO.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //READ OK
    @GetMapping(path="/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        Usuario user = userDAO.findByEmail(email);
        if(user == null) return new ResponseEntity<>(new CustomErrorType("Usuario não encontrado."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    //UPDATE OK
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Usuario user){
        userDAO.save(user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //DELETE OK
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable  long id){
        userDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/{email}")
    public ResponseEntity<?> deleteClientByEmail(@PathVariable String email){
        userDAO.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path="/getSenha")
    public ResponseEntity<?> getSenha(@RequestParam String email){
        System.out.println(email);
        Usuario user = this.userDAO.findByEmail(email);
        System.out.println(user.getSenha());
        try{
            this.mailSender.sendEmail(email, user.getSenha());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (MailException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }









}

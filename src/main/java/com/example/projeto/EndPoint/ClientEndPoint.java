package com.example.projeto.EndPoint;
import com.example.projeto.Error.CustomErrorType;
import com.example.projeto.Model.User;
import com.example.projeto.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static jdk.nashorn.internal.objects.Global.print;


@RestController
@RequestMapping("client")
public class ClientEndPoint {

    @Autowired
      UserRepository userDAO;

    //READ
    @RequestMapping
    public ResponseEntity<?> getListClient(){
        return new ResponseEntity<>(this.userDAO.findAll() , HttpStatus.OK);
    }

    //CREATE OK
    @RequestMapping(path ="/create")
    public ResponseEntity<?> createUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("senha") String senha){
        User test = userDAO.findByEmail(email);
        if(!(test==null)) return new ResponseEntity<>("Email ja cadastrado", HttpStatus.NOT_ACCEPTABLE);
        User user = new User(name,email,senha);
        return new ResponseEntity<>(userDAO.save(user), HttpStatus.CREATED);
    }

    //READ OK
    @GetMapping(path="/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        User user = userDAO.findByEmail(email);
        if(user == null) return new ResponseEntity<>(new CustomErrorType("Usuario não encontrado."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //UPDATE OK
    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
        userDAO.save(user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //DELETE OK
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable  long id){
        userDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

package com.example.projeto.EndPoint;
import com.example.projeto.Error.CustomErrorType;
import com.example.projeto.Model.Usuario;
import com.example.projeto.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("client")
public class ClientEndPoint {

    @Autowired
    Environment environment;
    @Autowired
      UserRepository userDAO;


    //READ
    @GetMapping(path="/getAll")
    public ResponseEntity<?> getListClient(@RequestParam String email){
        Usuario user = this.userDAO.findByEmail(email);
        System.out.println(user);
        if(user!=null && user.getAccessToken()!=null){
            return new ResponseEntity<>(this.userDAO.findAll() , HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("No Authorized."), HttpStatus.UNAUTHORIZED);
    }

    //CREATE OK
    @RequestMapping(path ="/create")
    public ResponseEntity<?> createUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("senha") String senha){
        Usuario test = userDAO.findByEmail(email);
        if(!(test==null)) return new ResponseEntity<>("Email ja cadastrado", HttpStatus.NOT_ACCEPTABLE);
        String salt = BCrypt.gensalt();
        senha = BCrypt.hashpw(senha, salt);
        Usuario user = new Usuario(name,email,senha);
        return new ResponseEntity<>(userDAO.save(user), HttpStatus.CREATED);
    }

    //READ OK
    @GetMapping(path="/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        Usuario user = userDAO.findByEmail(email);
        if(user == null) return new ResponseEntity<>(new CustomErrorType("Usuario não encontrado."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(path="/saveToken")
    public ResponseEntity<?> saveToken(@RequestParam String token, @RequestParam String email){
        Usuario user = this.userDAO.findByEmail(email);
        user.setAccessToken(token);
        this.userDAO.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //UPDATE OK
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Usuario user){
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

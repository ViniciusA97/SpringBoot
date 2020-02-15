package com.example.projeto.EndPoint;
import com.example.projeto.Error.CustomErrorType;
import com.example.projeto.Model.User;
import com.example.projeto.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("client")
public class ClientEndPoint {

    @Autowired
      UserRepository userDAO;

    @RequestMapping
    public ResponseEntity<?> getListClient(){
        return new ResponseEntity<>(this.userDAO.findAll() , HttpStatus.OK);
    }
    @RequestMapping(path ="/create")
    public ResponseEntity<?> createUser(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("senha") String senha){
        User user = new User(name, email,senha);
        User test = userDAO.findByEmail(email);
        if(user.getEmail().equals(email)) return new ResponseEntity<>("Email ja cadastrado", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(userDAO.save(user), HttpStatus.CREATED);
    }

    @GetMapping(path="/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        User user = userDAO.findByEmail(email);
        if(user == null) return new ResponseEntity<>(new CustomErrorType("Usuario não encontrado."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody User user){
        return new ResponseEntity<>(userDAO.save(user), HttpStatus.OK);
    }



}

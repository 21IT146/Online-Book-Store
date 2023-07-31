package com.book.store.Controllers;

import com.book.store.Entities.User;
import com.book.store.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){

        User b1=null;
        try {
            b1 = this.userService.adduser(user);
            System.out.println(user);
            return ResponseEntity.of(Optional.of(b1));
            //return ResponseEntity.status(HttpStatus.CREATED).build();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getuser()//@RequestHeader(value="accessKey") String accessKey in getBook()
    {
        List<User> list= this.userService.getAllUsers();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getuser(@PathVariable("id") int id)
    {

        User user =userService.getUserById(id);

        if(user ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }

    //searching User
    @GetMapping("/searchuser")
    public ResponseEntity<User> searchuser(@RequestParam(name="name") String name)
    {

        User user =userService.getUser(name);

        if(user ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }
    //delete user handler
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId){
        try {
            this.userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update user handler
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") int userId){
        try {
            this.userService.updateUser(user,userId);
            return ResponseEntity.ok().body(user);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}

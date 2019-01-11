package com.pd.eweltol.taskmanager.controller;


import com.pd.eweltol.taskmanager.model.User;
import com.pd.eweltol.taskmanager.service.UserService;
import com.pd.eweltol.taskmanager.validator.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity getUsers(){
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getUsers();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(userList);
    }


    @GetMapping("/users/id/{id}")
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable(value="id") String id){
        ArrayList<User> users = new ArrayList<>();
        Long searchId;
        try {
            searchId = new Long(id);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(users);
        }
        Optional<User> user = userService.getUser(searchId);
        if(user.isPresent()){
            users.add((user.get()));
            return ResponseEntity.ok().body(users);
        }else{
            return ResponseEntity.ok().body(users);
        }
    }

    @GetMapping("/users/username/{username}")
    @ResponseBody
    public ResponseEntity getUserByUsername(@PathVariable(value="username") String username){
        ArrayList<User> users = new ArrayList<>();
        User user = userService.getUserByUsername(username);
        if(user!=null){
            users.add((user));
            return ResponseEntity.ok().body(users);
        }else{
            return ResponseEntity.ok().body(users);
        }
    }

    @GetMapping("/users/role/{role}")
    @ResponseBody
    public ResponseEntity getUserByRole(@PathVariable(value="role") String role){

        ArrayList<User> users = userService.getUserByRole(role);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/lastname/{lastName}")
    @ResponseBody
    public ResponseEntity getUserByLastName(@PathVariable(value="lastName") String lastName){
        lastName=lastName.toUpperCase();
        ArrayList<User> users = userService.getUserByLastName(lastName);
        return ResponseEntity.ok().body(users);
    }


@GetMapping("/users/email/{email}")
    @ResponseBody
    public ResponseEntity getUserByEmail(@PathVariable(value="email") String email){
        email = email.toUpperCase();
    ArrayList<User> users = userService.getUserByEmail(email);
    return ResponseEntity.ok().body(users);
}

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
            try {
                userService.addUser(user);
            }catch(Exception e){
                return ResponseEntity.badRequest().body("{\"errors\" :[\"user exist\"]}");
            }
            return ResponseEntity.ok().body("{\"successfull\": \"added user\"}");
    }


    @PutMapping("/user")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user, Errors errors){

        try {
            userService.updateUser(user);
        }catch(Exception e){
            if(e.getClass()== NoResultException.class){
                return ResponseEntity.badRequest().body("{\"errors\" :[\"no user found\"]}");
            }
            return ResponseEntity.badRequest().body("{\"errors\" :[\"user with that parameters exist\"]}");
        }
        return ResponseEntity.ok().body("{\"successfull\": \"user updated\"}");
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable(value="id") String id){
        ArrayList<User> users = new ArrayList<>();
        Long deleteId;
        try {
            deleteId = new Long(id);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(false);
        }
        boolean result = userService.deleteUser(deleteId);
        return ResponseEntity.ok().body(result);
    }

}

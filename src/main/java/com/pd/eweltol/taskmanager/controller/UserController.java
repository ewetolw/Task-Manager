package com.pd.eweltol.taskmanager.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.eweltol.taskmanager.model.User;
import com.pd.eweltol.taskmanager.service.UserService;
import com.pd.eweltol.taskmanager.validator.ValidationError;
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
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
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


    @GetMapping("/getUser/{id}")
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable(value="id") Long id){

        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.badRequest().body("{\"errors\" :[\"no user found\"]}");
        }
    }


    @PostMapping("/addUser")
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

    @PutMapping("/updateUserData")
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
}

package com.pd.eweltol.taskmanager.service;

import com.pd.eweltol.taskmanager.model.Role;
import com.pd.eweltol.taskmanager.model.User;
import com.pd.eweltol.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username){ return userRepository.findByUsername(username); }

    public ArrayList<User> getUserByRole(String role){
        role = role.toUpperCase();
        Role searchRole;
        try {
            searchRole = Role.valueOf(role);
        }catch(Exception e){
            return new ArrayList<>();
        }
        ArrayList<User> users = userRepository.findAllByRole(searchRole);
        if(users!=null) {
            return users;
        }else{
            return new ArrayList<>();
        }
    }

    public ArrayList<User> getUserByEmail(String email){

        ArrayList<User> users = userRepository.findAllByEmail(email);
        if(users!=null) {
            return users;
        }else{
            return new ArrayList<>();
        }
    }

    public ArrayList<User> getUserByLastName(String lastName){
        ArrayList<User> users = userRepository.findAllByLastName(lastName);
        if(users!=null) {
            return users;
        }else{
            return new ArrayList<>();
        }
    }


    public void addUser(User user){
        user = UserService.upperCaseFields(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void updateUser(User user){
           Optional<User> userDB = userRepository.findById(user.getId());
            if(userDB.isPresent()){
                userDB.get().updateUserData(user);
                userRepository.save(userDB.get());
            }else{
                throw new NoResultException();
            }
    }

    public boolean deleteUser(Long id){
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.delete(u.get());
            return true;
        }
        return false;
    }

    private static User upperCaseFields(User user){
        user.setLastName(user.getLastName().toUpperCase());
        user.setFirstName(user.getFirstName().toUpperCase());
        user.setEmail(user.getEmail().toUpperCase());
        return user;
    }

}

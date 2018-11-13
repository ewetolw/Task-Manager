package com.pd.eweltol.taskmanager.service;

import com.pd.eweltol.taskmanager.model.User;
import com.pd.eweltol.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public void addUser(User user){
            userRepository.save(user);
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
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



}

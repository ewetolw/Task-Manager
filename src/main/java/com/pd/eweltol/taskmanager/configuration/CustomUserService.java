package com.pd.eweltol.taskmanager.configuration;


import org.springframework.security.core.userdetails.User;
import com.pd.eweltol.taskmanager.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.pd.eweltol.taskmanager.model.User user = userRepository.findByUsername(username);
        if(user!=null){
            List<GrantedAuthority> authorityList = new ArrayList<>();
            System.out.println(user.getRole().toString());
            authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
            return new User(user.getUsername(), user.getPassword(), authorityList);
        }
        throw new UsernameNotFoundException("User: "+ username+" doesn't exist");
    }
}

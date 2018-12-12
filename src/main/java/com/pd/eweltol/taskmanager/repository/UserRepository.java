package com.pd.eweltol.taskmanager.repository;

import com.pd.eweltol.taskmanager.model.types.Role;
import com.pd.eweltol.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
        ArrayList<User> findAllByRole(Role role);
        ArrayList<User> findAllByEmail(String email);
        ArrayList<User> findAllByLastName(String lastName);
       // void save(User user, Long id);
}

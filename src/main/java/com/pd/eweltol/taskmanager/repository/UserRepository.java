package com.pd.eweltol.taskmanager.repository;

import com.pd.eweltol.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
       // void save(User user, Long id);
}

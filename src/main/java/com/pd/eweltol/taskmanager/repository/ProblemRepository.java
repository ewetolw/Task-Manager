package com.pd.eweltol.taskmanager.repository;

import com.pd.eweltol.taskmanager.model.Problem;
import com.pd.eweltol.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    List<Problem> findAllByPrincipal(User user);

}

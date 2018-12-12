package com.pd.eweltol.taskmanager.repository;

import com.pd.eweltol.taskmanager.model.Task;
import com.pd.eweltol.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {



    List<Task> findAllByContractor(User user);



}

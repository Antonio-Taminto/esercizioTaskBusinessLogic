package com.esercizioTaskBusinessLogic.repository;

import com.esercizioTaskBusinessLogic.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}

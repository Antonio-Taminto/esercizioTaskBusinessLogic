package com.esercizioTaskBusinessLogic.servicies;

import com.esercizioTaskBusinessLogic.entities.Task;
import com.esercizioTaskBusinessLogic.repository.TaskRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    public Task save(Task task){
        return repo.save(task);
    }
    public List<Task> getAllTask(){
        return repo.findAll();
    }
    public Optional<Task> getTaskFromId(Long id){
        Optional<Task> taskOptional = repo.findById(id);
        if(taskOptional.isPresent()){
            return taskOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<Task> updateTask(Long id,Task task){
        Optional<Task> taskOptional = getTaskFromId(id);
        if(taskOptional.isPresent()){
            taskOptional.get().setTitle(task.getTitle());
            taskOptional.get().setDescription(task.getDescription());
            taskOptional.get().setDueDate(task.getDueDate());
            taskOptional.get().setCompleted(task.isCompleted());
            Task updatedTask = repo.save(taskOptional.get());
            return Optional.of(updatedTask);
        }else {
            return Optional.empty();
        }
    }

    public Optional<Task> deleteTaskFromId(Long id){
        Optional<Task> taskOptional = getTaskFromId(id);
        if(taskOptional.isPresent()){
            repo.delete(taskOptional.get());
            return taskOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<Task> updateTaskCompletation(Long id,boolean isCompleted){
        Optional<Task> taskOptional = getTaskFromId(id);
        if(taskOptional.isPresent()){
            taskOptional.get().setCompleted(isCompleted);
            Task updatedTask = repo.save(taskOptional.get());
            return Optional.of(updatedTask);
        }else {
            return Optional.empty();
        }
    }
}

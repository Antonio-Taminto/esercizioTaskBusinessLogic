package com.esercizioTaskBusinessLogic.controllers;

import com.esercizioTaskBusinessLogic.entities.Task;
import com.esercizioTaskBusinessLogic.servicies.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok().body(service.save(task));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.ok().body(service.getAllTask());
    }
    @GetMapping("/getfromid/{id}")
    public ResponseEntity<Task> getTaskFromId(@PathVariable Long id){
        Optional<Task> taskOptional = service.getTaskFromId(id);
        if(taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/updatetask/{id}")
    public ResponseEntity<Task> updateTaskFromId(@PathVariable Long id,@RequestBody Task task){
        Optional<Task> taskOptional = service.updateTask(id,task);
        if(taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteTaskFromId(@PathVariable Long id){
        Optional<Task> taskOptional = service.deleteTaskFromId(id);
        if(taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/changecompletation/{id}")
    public ResponseEntity<Task> updateTaskCompletation(
            @PathVariable Long id,@RequestParam boolean isCompleted){
        Optional<Task> taskOptional = service.updateTaskCompletation(id,isCompleted);
        if(taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}

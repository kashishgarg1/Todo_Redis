package com.example.todo_redis.Todo_redis.controllers;

import com.example.todo_redis.Todo_redis.model.Todo;
import com.example.todo_redis.Todo_redis.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("tasks")
    public Iterable<Todo> all() {
        return taskService.findAll();
    }

    @PutMapping("tasks/{id}")
    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
        return taskService.update(id, todo);
    }

    @DeleteMapping("tasks/{id}")
    public void delete(@PathVariable String id) {
        taskService.delete(id);
    }

    @PostMapping("tasks")
    public Todo create(@RequestBody Todo todo) {
        return taskService.create(todo);
    }
}

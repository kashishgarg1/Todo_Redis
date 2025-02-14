
package com.example.todo_redis.Todo_redis.controllers;

import com.example.todo_redis.Todo_redis.model.Todo;
import com.example.todo_redis.Todo_redis.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TodoRepo repository;

    @GetMapping("tasks")
    Iterable<Todo> all() {
        return repository.findAll();
    }

    @PutMapping("tasks/{id}")
    Todo update(@PathVariable String id, @RequestBody Map<String, String> body) {
        var result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find task");
        }
        var todo = result.get();
        todo.name = body.get("name"); // Update the task name
        todo.completed = Boolean.parseBoolean(body.get("completed"));

        return repository.save(todo);
    }

    @DeleteMapping("tasks/{id}")
    void delete(@PathVariable String id) {
        var result = repository.findById(id);
        result.ifPresent(todo -> repository.delete(todo));
    }

    @PostMapping("tasks")
    Todo create(@RequestBody Map<String, String> body) {
        var todo = new Todo();
        todo.name = body.get("name");
        return repository.save(todo);
    }
}


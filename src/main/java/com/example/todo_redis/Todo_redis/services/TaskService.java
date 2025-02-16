package com.example.todo_redis.Todo_redis.services;

import com.example.todo_redis.Todo_redis.model.Todo;
import com.example.todo_redis.Todo_redis.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TodoRepo repository;

    public Iterable<Todo> findAll() {
        return repository.findAll();
    }

    public Todo update(String id, Todo todo) {
        Optional<Todo> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task");
        }
        Todo existingTodo = result.get();
        existingTodo.name = todo.name;
        existingTodo.completed = todo.completed;

        return repository.save(existingTodo);
    }


    public void delete(String id) {
        Optional<Todo> result = repository.findById(id);
        if (result.isPresent()) {
            repository.delete(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task");
        }
    }

    public Todo create(Todo todo) {
        return repository.save(todo);
    }
}

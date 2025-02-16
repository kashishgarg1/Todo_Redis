package com.example.todo_redis.Todo_redis.services;

import com.example.todo_redis.Todo_redis.model.Todo;
import com.example.todo_redis.Todo_redis.repositories.TodoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// creating a real object in the test is not feasible. we will create a mock object - mockito - create a mock object for us
@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    TodoRepo repository;
    @InjectMocks
    TaskService taskService ;

    @Test
    public void getList() {
        System.out.println("getList");
        Todo todo = new Todo();
        Todo addedtodo = new Todo();
        todo.id = "1";
        todo.name = "Drink water";
        todo.completed = true;
        addedtodo.id = "2";
        addedtodo.name = "lunch";
        addedtodo.completed = false;
//        taskService.findAll();

        Mockito.when(repository.findAll()).thenReturn(List.of(todo));
        Assertions.assertNotNull(addedtodo);
        assertEquals(todo.id,addedtodo.id);
        assertEquals(todo.name,addedtodo.name);
        assertEquals(todo.completed,addedtodo.completed);
    }

    @Test
    public void deleteTask_TodoDoesNotExist() {
        System.out.println("deleteTask");
        String todoId = "3";
        Mockito.when(repository.findById(todoId)).thenReturn(Optional.empty());


        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> taskService.delete(todoId)
        );

        assertEquals("Unable to find task", thrown.getReason());
    }
    @Test
    public void deleteTaskTodoExist() {
        System.out.println("deleteTask");
        Todo todo = new Todo();
        Todo addedtodo = new Todo();
        todo.id = "1";
        todo.name = "Drink water";
        todo.completed = true;
         Mockito.when(repository.findById(todo.id)).thenReturn(Optional.of(todo));
         taskService.delete("1");
        verify(repository, times(1)).delete(todo);
    }
    @Test
    public void updateTask() {
        System.out.println("updateTask");
        Todo existingTodo = new Todo();
        existingTodo.id = "1";
        existingTodo.name = "Drink";
        existingTodo.completed = true;


        Todo updatedTodo = new Todo();
        updatedTodo.id = "1";
        updatedTodo.name = "Music";
        Mockito.when(repository.save(existingTodo)).thenReturn(existingTodo);

        Todo result = taskService.update(existingTodo.id, updatedTodo);

        verify(repository, times(1)).findById(existingTodo.id);
        verify(repository, times(1)).save(existingTodo);


        System.out.println("Expected: " + updatedTodo.name);
        System.out.println("Actual: " + result.name);
        assertEquals(updatedTodo.name, result.name);
    }
    @Test
    public void addTask(){
        System.out.println("addTask");
        Todo todo = new Todo();
        todo.id = "5";
        todo.name = "movie";
        todo.completed = true;

        Mockito.when(repository.save(todo)).thenReturn(todo);
        Todo addedtodo = taskService.create(todo);
        Assertions.assertEquals(todo.id, addedtodo.id);


    }

}
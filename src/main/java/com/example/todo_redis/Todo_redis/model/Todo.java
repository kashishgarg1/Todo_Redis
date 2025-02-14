package com.example.todo_redis.Todo_redis.model;

import com.redis.om.spring.annotations.Document;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;


@Document
public class Todo {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     public String id;
     public String name;
     public boolean completed = false;
}

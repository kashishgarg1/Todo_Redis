package com.example.todo_redis.Todo_redis.model;

import com.redis.om.spring.annotations.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Document
//@Data
////@AllArgsConstructor
////@NoArgsConstructor
//@Entity
//@Table(name = "todo_table")
public class Todo implements Serializable {

//    @jakarta.persistence.Id
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     public String id;
     public String name;
     public boolean completed = false; // Default value is fine
}
package com.example.todo_redis.Todo_redis.repositories;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.example.todo_redis.Todo_redis.model.Todo;

public interface TodoRepo  extends RedisDocumentRepository<Todo, String>{

}

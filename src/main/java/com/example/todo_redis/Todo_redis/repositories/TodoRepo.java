package com.example.todo_redis.Todo_redis.repositories;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.example.todo_redis.Todo_redis.model.Todo;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
public interface TodoRepo  extends RedisDocumentRepository<Todo, String>{

}

package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository <Task, UUID> {

    List<Task> findAllByOrderByCreatedAtAsc();

    }

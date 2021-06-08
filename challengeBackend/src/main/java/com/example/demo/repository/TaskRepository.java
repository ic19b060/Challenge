package com.example.demo.repository;

import com.example.demo.model.Task;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface TaskRepository extends CrudRepository <Task, UUID> {

    List<Task> findAllByOrderByCreatedAtAsc();

    }

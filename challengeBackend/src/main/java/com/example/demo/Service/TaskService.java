package com.example.demo.Service;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Task createTask(LocalDateTime updatedAt,LocalDateTime dueDate,LocalDateTime resolvedAT,String title ,String description,Integer priority, String status){
        return taskRepository.save(new Task(UUID.randomUUID(),LocalDateTime.now(), updatedAt,dueDate,resolvedAT,title,description, priority, status));
    }

    public List<Task> findallTasks(){
        return taskRepository.findAllByOrderByCreatedAtAsc();
    }

    public Optional<Task> getById(UUID id) {
        return taskRepository.findById(id);
    }

    public void deleteTask (UUID id){
       taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Task with id %s not found", id.toString()))
        );
        taskRepository.deleteById(id);
    }

    public Task updateTask(TaskDTO taskDTO) {
        Task taskToUpdate = modelMapper.map(taskDTO, Task.class);
        Task oldtask;
        try {
             oldtask = taskRepository.findById(taskToUpdate.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Task not found"))
            );
        } catch (IllegalArgumentException e) {
            log.error("Cannot parse id from string to UUID");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        taskToUpdate.setCreatedAt(oldtask.getCreatedAt());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(taskToUpdate);
    }
}

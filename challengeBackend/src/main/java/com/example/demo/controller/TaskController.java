package com.example.demo.controller;

import com.example.demo.DTO.TaskDTO;
import com.example.demo.Service.TaskService;
import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TaskController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final TaskService taskService;


   @PostMapping("/task/create")
   TaskDTO createTask(
            @RequestBody TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task newTask = taskService.createTask(task.getUpdatedAt(),task.getDueDate(),task.getResolvedAT(),task.getTitle(),task.getDescription(),task.getPriority(),task.getStatus());
        return modelMapper.map(newTask, TaskDTO.class);
    }

    @GetMapping("/task")
    List<TaskDTO> listAllTasks(){
        return taskService.findallTasks().stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/task/{taskID}")
    TaskDTO fetchSingleTask(@PathVariable String taskID) {
        Optional<Task> task = taskService.getById(UUID.fromString(taskID));
        return modelMapper.map(task.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Task not found"))), TaskDTO.class);
    }

   @DeleteMapping("/task/{taskID}")
   void deleteTask(@PathVariable String taskID) {
        taskService.deleteTask(UUID.fromString(taskID));
    }

   @PutMapping("/task")
    TaskDTO updateTask(
            @RequestBody TaskDTO taskDTO)  {
        Task task = taskService.updateTask(taskDTO);
        return modelMapper.map(task, TaskDTO.class);
    }
}

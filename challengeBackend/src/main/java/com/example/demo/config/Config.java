package com.example.demo.config;

import com.example.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class Config {

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedDelayTask() {
    taskService.createTask(LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"TaskDemo","TaskNumber1",2,"New");
    }

}
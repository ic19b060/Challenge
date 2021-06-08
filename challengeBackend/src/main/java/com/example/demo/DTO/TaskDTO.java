package com.example.demo.DTO;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

        @NotBlank
        private UUID id;

        @NotBlank
        @CreatedDate
        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;


        private LocalDateTime resolvedAT;


        private LocalDateTime dueDate;

        @NotBlank
        private String title;

        @NotBlank
        private String description;

        private int priority;

        @NotBlank
        private String status;


    }


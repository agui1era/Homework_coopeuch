package com.taskManager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotBlank(message = "Description cannot be empty")
public class RequestDTO {
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private boolean active;
}

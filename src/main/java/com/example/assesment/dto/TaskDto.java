package com.example.assesment.dto;

import com.example.assesment.enums.Status;
import com.example.assesment.enums.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class TaskDto {
    private String id;
    private String title;
    private String staffId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;

    private List<String> comments;
    private List<String> activityLogs;


}


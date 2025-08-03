package com.example.assesment.model;

import com.example.assesment.enums.Status;
import com.example.assesment.enums.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String id;
    private String title;
    private String staffId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Status status;
    private Priority priority = Priority.MEDIUM;

    private List<String> comments = new ArrayList<>();
    private List<String> activityLogs = new ArrayList<>();
}


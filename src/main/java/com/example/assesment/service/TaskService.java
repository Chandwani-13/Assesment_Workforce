package com.example.assesment.service;

import com.example.assesment.dto.CreateTaskRequest;
import com.example.assesment.dto.TaskDto;
import com.example.assesment.enums.Priority;
import com.example.assesment.enums.Status;
import com.example.assesment.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final Map<String, Task> taskMap = new HashMap<>();

    public TaskDto createTask(CreateTaskRequest request) {
        Task task = new Task(
                UUID.randomUUID().toString(),
                request.getTitle(),
                request.getStaffId(),
                request.getStartDate(),
                request.getDueDate(),
                Status.ACTIVE,
                Priority.MEDIUM,
                new ArrayList<>(),
                new ArrayList<>(List.of("Task created"))
        );
        taskMap.put(task.getId(), task);
        return toDto(task);
    }

    public List<TaskDto> getAllTasks() {
        return taskMap.values().stream()
                .filter(task -> task.getStatus() != Status.CANCELLED) // ✅ filter here
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(String id) {
        Task task = taskMap.get(id);
        return task != null ? toDto(task) : null;
    }

    public void updatePriority(String id, Priority priority) {
        Task task = taskMap.get(id);
        if (task != null) {
            task.setPriority(priority);
            task.getActivityLogs().add("Priority changed to " + priority);
        }
    }

    public void addComment(String id, String comment) {
        Task task = taskMap.get(id);
        if (task != null) {
            task.getComments().add(comment);
            task.getActivityLogs().add("Comment added: " + comment);
        }
    }

    public List<TaskDto> getTasksByPriority(Priority priority) {
        return taskMap.values().stream()
                .filter(task -> task.getPriority() == priority)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void reassignTask(String taskId, String newStaffId) {
        Task oldTask = taskMap.get(taskId);
        if (oldTask != null) {
            // Mark old task as CANCELLED
            oldTask.setStatus(Status.CANCELLED);
            oldTask.getActivityLogs().add("Task reassigned from " + oldTask.getStaffId() + " to " + newStaffId);


            Task newTask = new Task(
                    UUID.randomUUID().toString(),
                    oldTask.getTitle(),
                    newStaffId,
                    oldTask.getStartDate(),
                    oldTask.getDueDate(),
                    Status.ACTIVE,
                    oldTask.getPriority(),
                    new ArrayList<>(oldTask.getComments()),
                    new ArrayList<>(List.of("Task reassigned to " + newStaffId))
            );

            taskMap.put(newTask.getId(), newTask);
        }
    }

    public List<TaskDto> getSmartDailyTasks(LocalDate from, LocalDate to) {
        return taskMap.values().stream()
                .filter(task -> task.getStatus() == Status.ACTIVE)
                .filter(task ->

                        (!task.getStartDate().isBefore(from) && !task.getStartDate().isAfter(to)) || task.getStartDate().isBefore(from)
                )
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    private TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStaffId(task.getStaffId());
        dto.setStartDate(task.getStartDate());
        dto.setDueDate(task.getDueDate());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setComments(task.getComments());
        dto.setActivityLogs(task.getActivityLogs());
        return dto;
    }


}


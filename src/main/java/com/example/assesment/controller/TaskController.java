package com.example.assesment.controller;

import com.example.assesment.dto.CreateTaskRequest;
import com.example.assesment.dto.TaskDto;
import com.example.assesment.enums.Priority;
import com.example.assesment.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable String id) {
        TaskDto dto = taskService.getTaskById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/priority")
    public ResponseEntity<?> updatePriority(@PathVariable String id, @RequestParam Priority priority) {
        taskService.updatePriority(id, priority);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable String id, @RequestBody String comment) {
        taskService.addComment(id, comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskDto>> getByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }
    @PostMapping("/{id}/reassign")
    public ResponseEntity<?> reassignTask(@PathVariable String id, @RequestParam String newStaffId) {
        taskService.reassignTask(id, newStaffId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/smart-daily")
    public ResponseEntity<List<TaskDto>> getSmartDailyTasks(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return ResponseEntity.ok(taskService.getSmartDailyTasks(from, to));
    }


}


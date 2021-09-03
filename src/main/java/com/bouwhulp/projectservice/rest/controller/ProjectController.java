package com.bouwhulp.projectservice.rest.controller;

import com.bouwhulp.projectservice.rest.model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(List.of(Project.builder().build()));
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<List<Project>> getProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(List.of(Project.builder().build()));
    }
}

package com.bouwhulp.projectservice.rest.controller;

import com.bouwhulp.projectservice.repository.ToolRepository;
import com.bouwhulp.projectservice.repository.model.MaterialEntity;
import com.bouwhulp.projectservice.repository.model.ToolEntity;
import com.bouwhulp.projectservice.rest.model.MaterialDto;
import com.bouwhulp.projectservice.rest.model.ToolDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ToolController {

    private final ToolRepository toolRepository;

    public ToolController(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @GetMapping("/tools")
    public ResponseEntity<List<ToolDto>> getTools() {
        return ResponseEntity.ok(toolRepository.findAll().stream()
                .map(ToolEntity::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("/tools")
    public ResponseEntity<ToolDto> postTool(@RequestBody ToolDto toolDto) {
        ToolEntity toolEntity = toolRepository.saveAndFlush(toolDto.toEntity());

        return ResponseEntity.ok(toolEntity.toDto());
    }

    @DeleteMapping("/tools/{toolId}")
    public ResponseEntity<Void> deleteTool(@PathVariable UUID toolId) {
        toolRepository.deleteById(toolId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/tools/{toolId}")
    public ResponseEntity<ToolDto> getTool(@PathVariable UUID toolId) {
        Optional<ToolEntity> optionalToolEntity = toolRepository.findById(toolId);

        return optionalToolEntity
                .map(toolEntity -> ResponseEntity.ok(toolEntity.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

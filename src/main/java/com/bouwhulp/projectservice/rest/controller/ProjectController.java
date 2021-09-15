package com.bouwhulp.projectservice.rest.controller;

import com.bouwhulp.projectservice.repository.MaterialRepository;
import com.bouwhulp.projectservice.repository.ProjectRepository;
import com.bouwhulp.projectservice.repository.ToolRepository;
import com.bouwhulp.projectservice.repository.model.MaterialEntity;
import com.bouwhulp.projectservice.repository.model.MaterialRecipeEntity;
import com.bouwhulp.projectservice.repository.model.ProjectEntity;
import com.bouwhulp.projectservice.repository.model.ToolEntity;
import com.bouwhulp.projectservice.rest.model.MaterialDto;
import com.bouwhulp.projectservice.rest.model.MaterialRecipeDto;
import com.bouwhulp.projectservice.rest.model.ProjectDto;
import com.bouwhulp.projectservice.rest.model.ToolDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final MaterialRepository materialRepository;
    private final ToolRepository toolRepository;

    public ProjectController (MaterialRepository materialRepository, ProjectRepository projectRepository, ToolRepository toolRepository) {
        this.projectRepository = projectRepository;
        this.materialRepository = materialRepository;
        this.toolRepository = toolRepository;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects() {
        return ResponseEntity.ok(projectRepository.findAll().stream()
                .map(ProjectEntity::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDto> postProjects(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectRepository.saveAndFlush(projectDto.toEntity());
        return ResponseEntity.ok(projectEntity.toDto());
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> putProjects(@PathVariable UUID projectId) {
        projectRepository.deleteById(projectId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable UUID projectId) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectId);

        return optionalProjectEntity
                .map(projectEntity -> ResponseEntity.ok(projectEntity.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/projects/{projectId}/materials")
    public ResponseEntity<List<MaterialDto>> getProjectMaterials(@PathVariable UUID projectId) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectId);

        return optionalProjectEntity
                .map(projectEntity -> ResponseEntity.ok(
                        projectEntity.toDto().getMaterialRecipes().stream()
                                .map(MaterialRecipeDto::getMaterial)
                                .collect(Collectors.toList())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/projects/{projectId}/materials")
    public ResponseEntity<Void> postProjectMaterial(@PathVariable UUID projectId, @RequestParam UUID materialId, @RequestParam(defaultValue = "1") int quantity) {
        Optional<MaterialEntity> optionalMaterialEntity = materialRepository.findById(materialId);
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectId);

        if (optionalMaterialEntity.isEmpty() || optionalProjectEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProjectEntity projectEntity = optionalProjectEntity.get();
        projectEntity.getMaterialRecipes().add(
                MaterialRecipeEntity.builder()
                        .material(optionalMaterialEntity.get())
                        .quantity(quantity)
                        .build());

        projectRepository.saveAndFlush(projectEntity);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects/{projectId}/tools")
    public ResponseEntity<List<ToolDto>> getProjectTools(@PathVariable UUID projectId) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectId);

        return optionalProjectEntity
                .map(projectEntity -> ResponseEntity.ok(
                        projectEntity.getTools().stream()
                                .map(ToolEntity::toDto)
                                .collect(Collectors.toList())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/projects/{projectId}/Tools")
    public ResponseEntity<Void> postProjectMaterial(@PathVariable UUID projectId, @RequestParam UUID toolId) {
        Optional<ToolEntity> optionalToolEntity = toolRepository.findById(toolId);
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectId);

        if (optionalToolEntity.isEmpty() || optionalProjectEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProjectEntity projectEntity = optionalProjectEntity.get();
        projectEntity.getTools().add(optionalToolEntity.get());

        projectRepository.saveAndFlush(projectEntity);

        return ResponseEntity.ok().build();
    }
}

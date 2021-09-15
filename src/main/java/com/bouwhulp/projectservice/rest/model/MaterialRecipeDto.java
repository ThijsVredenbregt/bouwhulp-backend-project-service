package com.bouwhulp.projectservice.rest.model;

import com.bouwhulp.projectservice.repository.model.MaterialRecipeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class MaterialRecipeDto {

    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private String description;
    private List<ProjectDto> projects;
    private LocalDate created;
    private LocalDate modified;
    private MaterialDto material;
    private int quantity;

    public MaterialRecipeEntity toEntity(){
        return MaterialRecipeEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .material(material.toEntity())
                .quantity(quantity)
                .projects(projects.stream().map(ProjectDto::toEntity).collect(Collectors.toList()))
                .created(created)
                .modified(modified)
                .build();
    }

}

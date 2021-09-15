package com.bouwhulp.projectservice.rest.model;

import com.bouwhulp.projectservice.repository.model.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class ProjectDto {

    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private String description;
    private LocalDate created;
    private LocalDate modified;
    private List<MaterialRecipeDto> materialRecipes;
    private List<ToolDto> tools;
    private HashMap<String, String> attributes;

    public ProjectEntity toEntity() {
        return ProjectEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .created(created)
                .modified(modified)
                .materialRecipes(materialRecipes.stream().map(MaterialRecipeDto::toEntity).collect(Collectors.toList()))
                .tools(tools.stream().map(ToolDto::toEntity).collect(Collectors.toList()))

                .build();
    }
}

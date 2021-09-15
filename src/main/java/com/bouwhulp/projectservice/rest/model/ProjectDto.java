package com.bouwhulp.projectservice.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
}

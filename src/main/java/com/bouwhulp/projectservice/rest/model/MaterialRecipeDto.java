package com.bouwhulp.projectservice.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
}

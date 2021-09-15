package com.bouwhulp.projectservice.rest.model;

import com.bouwhulp.projectservice.repository.model.ToolEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class ToolDto {

    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private String description;
    @GeneratedValue(generator = "uuid")
    private UUID sku;
    private String url;
    private Double price;
    private LocalDate created;
    private LocalDate modified;
    private List<ProjectDto> projects;
    private List<MaintenanceDto> maintenance;
    private HashMap<String, String> attributes;

    public ToolEntity toEntity() {
        return ToolEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .sku(sku)
                .url(url)
                .price(price)
                .created(created)
                .modified(modified)
                .projects(projects.stream().map(ProjectDto::toEntity).collect(Collectors.toList()))
                .maintenance(maintenance.stream().map(MaintenanceDto::toEntity).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

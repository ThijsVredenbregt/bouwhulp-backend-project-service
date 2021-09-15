package com.bouwhulp.projectservice.rest.model;

import com.bouwhulp.projectservice.repository.model.MaintenanceEntity;
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
public class MaintenanceDto {

    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private String description;
    private LocalDate created;
    private LocalDate modified;
    private List<MaterialDto> materials;
    private HashMap<String, String> attributes;

    public MaintenanceEntity toEntity() {
        return MaintenanceEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .created(created)
                .modified(modified)
                .materials(materials.stream().map(MaterialDto::toEntity).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

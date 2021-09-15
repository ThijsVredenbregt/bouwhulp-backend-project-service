package com.bouwhulp.projectservice.rest.model;

import com.bouwhulp.projectservice.repository.model.MaterialEntity;
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
public class MaterialDto {

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
    private DimensionsDto materialDimensions;
    private DimensionsDto packagingDimensions;
    private List<MaterialRecipeDto> materialRecipes;
    private List<MaintenanceDto> maintenance;
    private HashMap<String, String> attributes;

    public MaterialEntity toEntity() {
        return MaterialEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .created(created)
                .modified(modified)
                .sku(sku)
                .url(url)
                .price(price)
                .materialDimensions(materialDimensions.toEntity())
                .packagingDimensions(packagingDimensions.toEntity())
                .materialRecipes(materialRecipes.stream().map(MaterialRecipeDto::toEntity).collect(Collectors.toList()))
                .maintenance(maintenance.stream().map(MaintenanceDto::toEntity).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

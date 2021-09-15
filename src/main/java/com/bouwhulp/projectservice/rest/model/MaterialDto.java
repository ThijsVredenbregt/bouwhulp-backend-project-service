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
}

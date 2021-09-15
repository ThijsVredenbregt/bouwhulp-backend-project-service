package com.bouwhulp.projectservice.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import java.util.HashMap;
import java.util.UUID;

@Data
@Builder
public class DimensionsDto {

    @GeneratedValue(generator = "uuid")
    private UUID id;
    private int width;
    private int height;
    private int depth;
    private int weight;
    private HashMap<String, String> attributes;
}

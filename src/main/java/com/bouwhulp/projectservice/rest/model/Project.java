package com.bouwhulp.projectservice.rest.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Project {

    private UUID id;
}

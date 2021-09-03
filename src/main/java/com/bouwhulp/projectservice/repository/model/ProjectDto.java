package com.bouwhulp.projectservice.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class ProjectDto {

    @Id
    private UUID id;
}

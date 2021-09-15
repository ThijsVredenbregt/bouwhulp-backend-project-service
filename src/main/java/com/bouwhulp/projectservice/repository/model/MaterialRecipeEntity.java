package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.MaterialRecipeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Material_recipes")
public class MaterialRecipeEntity {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private MaterialEntity material;

    @NotNull
    @Column
    private int quantity;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProjectEntity> projects;

    @NotNull
    private LocalDate created;

    @NotNull
    private LocalDate modified;

    public MaterialRecipeDto toDto(){
        return MaterialRecipeDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .material(material.toDto())
                .quantity(quantity)
                .projects(projects.stream().map(ProjectEntity::toDto).collect(Collectors.toList()))
                .created(created)
                .modified(modified)
                .build();
    }
}

package com.bouwhulp.projectservice.repository.model;

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
}

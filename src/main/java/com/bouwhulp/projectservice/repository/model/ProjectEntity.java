package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.DimensionsDto;
import com.bouwhulp.projectservice.rest.model.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Projects")
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @NotNull
    @Column
    private LocalDate created;

    @NotNull
    @Column
    private LocalDate modified;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaterialRecipeEntity> materialRecipes;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ToolEntity> tools;

    @Transient
    private HashMap<String, String> attributes;

    public ProjectDto toDto(){
        return ProjectDto.builder()
                .id(id)
                .name(name)
                .created(created)
                .modified(modified)
                .description(description)
                .materialRecipes(materialRecipes.stream().map(MaterialRecipeEntity::toDto).collect(Collectors.toList()))
                .tools(tools.stream().map(ToolEntity::toDto).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

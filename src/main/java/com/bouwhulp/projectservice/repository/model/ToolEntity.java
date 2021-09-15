package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.ToolDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
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
@Table(name = "Tools")
public class ToolEntity {

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
    private Double price;

    @NotNull
    @Column
    private LocalDate created;

    @NotNull
    @Column
    private LocalDate modified;

    @NotNull
    @Column
    @Type(type="uuid-char")
    private UUID sku;

    @NotNull
    @Column
    private String url;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaintenanceEntity> maintenance;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProjectEntity> projects;

    @Transient
    private HashMap<String, String> attributes;

    public ToolDto toDto(){
        return ToolDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .sku(sku)
                .url(url)
                .created(created)
                .modified(modified)
                .projects(projects.stream().map(ProjectEntity::toDto).collect(Collectors.toList()))
                .maintenance(maintenance.stream().map(MaintenanceEntity::toDto).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.MaintenanceDto;
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
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "Maintenance")
public class MaintenanceEntity {

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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<MaterialEntity> materials;

    @Transient
    private HashMap<String, String> attributes;

    public MaintenanceDto toDto(){
        return MaintenanceDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .created(created)
                .modified(modified)
                .attributes(attributes)
                .materials(materials.stream().map(MaterialEntity::toDto).collect(Collectors.toList()))
                .build();
    }
}

package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.MaterialDto;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
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
@Table(name = "Materials")
public class MaterialEntity implements Serializable {

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
    @Column
    @Type(type="uuid-char")
    private UUID sku;

    @NotNull
    @Column
    private String url;

    @NotNull
    @Column
    private Double price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DimensionsEntity materialDimensions;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DimensionsEntity packagingDimensions;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY)
    private List<MaterialRecipeEntity>  materialRecipes;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaintenanceEntity> maintenance;

    @Transient
    private HashMap<String, String> attributes;

    public MaterialDto toDto(){
        return MaterialDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .created(created)
                .modified(modified)
                .sku(sku)
                .url(url)
                .materialDimensions(materialDimensions.toDto())
                .packagingDimensions(packagingDimensions.toDto())
                .materialRecipes(materialRecipes.stream().map(MaterialRecipeEntity::toDto).collect(Collectors.toList()))
                .maintenance(maintenance.stream().map(MaintenanceEntity::toDto).collect(Collectors.toList()))
                .attributes(attributes)
                .build();
    }
}

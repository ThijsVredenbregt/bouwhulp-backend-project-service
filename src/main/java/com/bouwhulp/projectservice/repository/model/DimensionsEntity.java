package com.bouwhulp.projectservice.repository.model;

import com.bouwhulp.projectservice.rest.model.DimensionsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dimensions")
public class DimensionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @NotNull
    @Column
    private int width;

    @NotNull
    @Column
    private int height;

    @NotNull
    @Column
    private int depth;

    @NotNull
    @Column
    private int weight;

    @Transient
    private HashMap<String, String> attributes;

    public DimensionsDto toDto(){
        return DimensionsDto.builder()
                .id(id)
                .width(width)
                .height(height)
                .depth(depth)
                .weight(weight)
                .attributes(attributes)
                .build();
    }
}

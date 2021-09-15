package com.bouwhulp.projectservice.rest.controller;

import com.bouwhulp.projectservice.repository.MaterialRepository;
import com.bouwhulp.projectservice.repository.model.MaterialEntity;
import com.bouwhulp.projectservice.rest.model.MaterialDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MaterialController {

    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @GetMapping("/materials")
    public ResponseEntity<List<MaterialDto>> getMaterials() {
        return ResponseEntity.ok(materialRepository.findAll().stream()
                .map()
                .collect(Collectors.toList()));
    }

    @PostMapping("/materials")
    public ResponseEntity<MaterialDto> postMaterial(@RequestBody MaterialDto materialDto) {
        MaterialEntity materialEntity = materialRepository.saveAndFlush();

        return ResponseEntity.ok();
    }

    @DeleteMapping("/materials/{materialId}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable UUID materialId) {
        materialRepository.deleteById(materialId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/materials/{materialId}")
    public ResponseEntity<MaterialDto> getMaterial(@PathVariable UUID materialId) {
        Optional<MaterialEntity> optionalMaterialDto = materialRepository.findById(materialId);

        return optionalMaterialDto
                .map(materialEntity -> ResponseEntity.ok())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

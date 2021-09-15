package com.bouwhulp.projectservice.repository;

import com.bouwhulp.projectservice.repository.model.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, UUID> {
}

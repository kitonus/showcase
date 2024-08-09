package com.jatis.showcase.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatis.showcase.entity.CaseEntity;

public interface CaseRepository extends JpaRepository<CaseEntity, UUID> {

	public Optional<CaseEntity> findByName(String name);
}

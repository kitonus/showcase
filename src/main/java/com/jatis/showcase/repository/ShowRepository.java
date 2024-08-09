package com.jatis.showcase.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatis.showcase.entity.CaseEntity;
import com.jatis.showcase.entity.ShowEntity;

public interface ShowRepository extends JpaRepository<ShowEntity, UUID> {
	
	List<ShowEntity> findByContainer(CaseEntity container);
}

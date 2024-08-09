package com.jatis.showcase.entity;

import java.time.ZonedDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

	private ZonedDateTime creationDate;
	
	@Column(length = 100)
	private String createdBy;
	
	private ZonedDateTime updateDate;

	@Column(length = 100)
	private String updatedBy;
	
	@PrePersist
	void prePersist() {
		this.creationDate = ZonedDateTime.now();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		this.createdBy = auth == null ? this.createdBy : auth.getName();
		this.updateDate = this.creationDate;
		this.updatedBy = this.createdBy;
	}
	
	@PreUpdate
	void preUpdate() {
		this.updateDate = ZonedDateTime.now();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		this.updatedBy = auth == null ? this.updatedBy : auth.getName();
	}
}

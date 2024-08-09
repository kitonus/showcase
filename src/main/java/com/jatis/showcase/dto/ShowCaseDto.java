package com.jatis.showcase.dto;

import java.util.List;

import com.jatis.showcase.entity.CaseEntity;
import com.jatis.showcase.entity.ShowEntity;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCaseDto {

	@Valid
	private List<ShowEntity> shows;
	
	@Valid
	private CaseEntity container;
}

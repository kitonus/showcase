package com.jatis.showcase.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.showcase.dto.ResponseDto;
import com.jatis.showcase.dto.ShowCaseDto;
import com.jatis.showcase.service.ShowCaseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/showcase")
@SecurityRequirement(name = "Bearer token")
@RestController
@RequiredArgsConstructor
public class ShowCaseController {

	private final ShowCaseService service;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseDto<ShowCaseDto> save(@Valid @RequestBody ShowCaseDto dto) {
		return ResponseDto.getInstance(service.save(dto));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/case/{caseName}")
	public ResponseDto<ShowCaseDto> getByCase(@PathVariable String caseName) {
		return ResponseDto.getInstance(service.getByCase(caseName));
	}
}

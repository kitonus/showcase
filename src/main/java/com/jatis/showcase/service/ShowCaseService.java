package com.jatis.showcase.service;

import org.springframework.transaction.annotation.Transactional;

import com.jatis.showcase.dto.ShowCaseDto;

public interface ShowCaseService {

	ShowCaseDto save(ShowCaseDto dto);

	ShowCaseDto getByCase(String caseName);

}
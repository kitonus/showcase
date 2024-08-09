package com.jatis.showcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.showcase.dto.ShowCaseDto;
import com.jatis.showcase.entity.CaseEntity;
import com.jatis.showcase.entity.ShowEntity;
import com.jatis.showcase.repository.CaseRepository;
import com.jatis.showcase.repository.ShowRepository;

@Component
public class ShowCaseServiceImpl implements ShowCaseService {

	@Autowired
	private ShowRepository showRepo;
	
	@Autowired
	private CaseRepository caseRepo;
	
	@Override
	@Transactional
	public ShowCaseDto save(ShowCaseDto dto) {
		CaseEntity caseEnt = this.caseRepo.save(dto.getContainer());
		for (ShowEntity s : dto.getShows()) {
			s.setContainer(caseEnt);
			this.showRepo.save(s);
		}
		return getByCase(dto.getContainer().getName());
	}
	
	@Override
	public ShowCaseDto getByCase(String caseName) {
		ShowCaseDto dto = new ShowCaseDto();
		CaseEntity caseEnt = caseRepo.findByName(caseName).orElse(null);
		if (caseEnt == null) {
			return null;
		}
		List<ShowEntity> shows = showRepo.findByContainer(caseEnt);
		dto.setContainer(caseEnt);
		dto.setShows(shows);
		return dto;
	}
}

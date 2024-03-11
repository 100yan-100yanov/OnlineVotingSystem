package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.CandidateAddDTO;
import com.vote.onlinevotingsystem.model.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {
    void add(CandidateAddDTO candidateAddDTO);

    void delete(Long id);

    List<CandidateDTO> getCandidates(String position);
}

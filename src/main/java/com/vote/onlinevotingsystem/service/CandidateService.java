package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.CandidateAddDTO;

public interface CandidateService {
    void add(CandidateAddDTO candidateAddDTO);

    void delete(Long id);
}

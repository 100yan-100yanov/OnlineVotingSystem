package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.CandidateAddDTO;
import com.vote.onlinevotingsystem.model.entity.Candidate;

import java.util.List;

public interface CandidateService {
    void add(CandidateAddDTO candidateAddDTO);

    void delete(Long id);

    List<String> getCandidatesNames(String position);

    List<Candidate> getCandidates(String position);
}

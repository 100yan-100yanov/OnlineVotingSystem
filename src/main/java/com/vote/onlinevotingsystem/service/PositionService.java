package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.CandidateDTO;
import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;

import java.util.List;

public interface PositionService {
    void add(PositionAddDTO positionAddDTO);

    void delete(Long id);

    Integer getTotalVotes(List<CandidateDTO> candidates);
}

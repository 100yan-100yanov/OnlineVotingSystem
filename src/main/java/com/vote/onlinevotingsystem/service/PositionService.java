package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;

import java.util.List;

public interface PositionService {
    void add(PositionAddDTO positionAddDTO);

    void delete(Long id);

    Integer getTotalVotes(String position);

    List<String> getPositions();
}

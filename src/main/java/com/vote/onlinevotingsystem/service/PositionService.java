package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;

public interface PositionService {
    void add(PositionAddDTO positionAddDTO);

    void delete(Long id);
}

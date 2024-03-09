package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;
import com.vote.onlinevotingsystem.model.entity.Position;
import com.vote.onlinevotingsystem.repository.PositionRepository;
import com.vote.onlinevotingsystem.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void add(PositionAddDTO positionAddDTO) {
        Position isRegistered = positionRepository.findPositionByType(positionAddDTO.getType());

        if (isRegistered != null) {
            throw new IllegalArgumentException(
                    "Position " + isRegistered.getType() + " already exists!"
            );
        }

        Position position = mapToPosition(positionAddDTO);

        positionRepository.save(position);
    }

    @Override
    public void delete(Long id) {
        Optional<Position> position = positionRepository.findById(id);

        if (position.isEmpty()) {
            throw new IllegalArgumentException(
                    "Position with id " + id + " doesn't exist!"
            );
        }

        positionRepository.delete(position.get());
    }

    private Position mapToPosition(PositionAddDTO positionAddDTO) {
        Position position = new Position();

        position.setType(positionAddDTO.getType());

        return position;
    }
}

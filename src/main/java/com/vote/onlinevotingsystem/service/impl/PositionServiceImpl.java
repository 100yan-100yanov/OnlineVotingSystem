package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;
import com.vote.onlinevotingsystem.model.entity.Candidate;
import com.vote.onlinevotingsystem.model.entity.Position;
import com.vote.onlinevotingsystem.repository.PositionRepository;
import com.vote.onlinevotingsystem.service.CandidateService;
import com.vote.onlinevotingsystem.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final CandidateService candidateService;

    public PositionServiceImpl(PositionRepository positionRepository,
                               CandidateService candidateService) {
        this.positionRepository = positionRepository;
        this.candidateService = candidateService;
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

    @Override
    public Integer getTotalVotes(String position) {
        return candidateService.getCandidates(position)
                .stream()
                .map(Candidate::getVotes)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<String> getPositions() {
        return positionRepository.findAll()
                .stream()
                .map(Position::getType)
                .toList();
    }

    private Position mapToPosition(PositionAddDTO positionAddDTO) {
        Position position = new Position();

        position.setType(positionAddDTO.getType());

        return position;
    }
}

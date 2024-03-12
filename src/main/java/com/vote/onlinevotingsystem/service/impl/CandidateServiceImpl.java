package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.CandidateAddDTO;
import com.vote.onlinevotingsystem.model.entity.Candidate;
import com.vote.onlinevotingsystem.model.entity.Position;
import com.vote.onlinevotingsystem.repository.CandidateRepository;
import com.vote.onlinevotingsystem.repository.PositionRepository;
import com.vote.onlinevotingsystem.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PositionRepository positionRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository,
                                PositionRepository positionRepository) {

        this.candidateRepository = candidateRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void add(CandidateAddDTO candidateAddDTO) {
        String firstName = candidateAddDTO.getFirstName();
        String lastName = candidateAddDTO.getLastName();

        Optional<Candidate> checkCandidate = candidateRepository
                .findByFirstNameAndLastName(firstName, lastName);

        if (checkCandidate.isPresent()) {
            throw new IllegalArgumentException(
                    "Candidate with name " + firstName + " " + lastName + " already exist!"
            );
        }

        Position position = positionRepository.findPositionByType(candidateAddDTO.getPosition());

        Candidate candidate = mapToCandidate(candidateAddDTO, position);
        candidateRepository.save(candidate);
    }

    @Override
    public void delete(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if (candidate.isEmpty()) {
            throw new IllegalArgumentException(
                    "Candidate with id " + id + " doesn't exist!"
            );
        }

        candidateRepository.delete(candidate.get());
    }

    @Override
    public List<String> getCandidatesNames(String position) {
        return candidateRepository.findByPosition_Type(position)
                .stream()
                .map(candidate -> candidate.getFirstName() + " " + candidate.getLastName())
                .toList();
    }

    @Override
    public List<Candidate> getCandidates(String position) {
        return candidateRepository.findByPosition_Type(position);
    }

    private Candidate mapToCandidate(CandidateAddDTO candidateAddDTO, Position position) {
        Candidate candidate = new Candidate();

        candidate.setFirstName(candidateAddDTO.getFirstName());
        candidate.setLastName(candidateAddDTO.getLastName());
        candidate.setPosition(position);

        return candidate;
    }
}

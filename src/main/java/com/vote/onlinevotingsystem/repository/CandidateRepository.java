package com.vote.onlinevotingsystem.repository;

import com.vote.onlinevotingsystem.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByFirstNameAndLastName(String firstName, String lastName);
}

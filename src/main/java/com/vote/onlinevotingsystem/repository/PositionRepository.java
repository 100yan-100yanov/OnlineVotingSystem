package com.vote.onlinevotingsystem.repository;

import com.vote.onlinevotingsystem.model.entity.Position;
import com.vote.onlinevotingsystem.model.enums.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findPositionByType(PositionType position);
}

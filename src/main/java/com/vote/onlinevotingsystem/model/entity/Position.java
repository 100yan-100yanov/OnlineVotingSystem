package com.vote.onlinevotingsystem.model.entity;

import com.vote.onlinevotingsystem.model.enums.PositionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PositionType type;

    public PositionType getType() {
        return type;
    }

    public void setType(PositionType type) {
        this.type = type;
    }
}

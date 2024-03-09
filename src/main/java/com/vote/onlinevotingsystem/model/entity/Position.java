package com.vote.onlinevotingsystem.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity{

    @Column(nullable = false)
    @Size(min = 4, max = 30)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

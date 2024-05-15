package com.vote.onlinevotingsystem.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity{

    @ManyToOne(targetEntity = User.class)
    private User voter;

    @ManyToOne(targetEntity = Position.class)
    private Position position;

    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

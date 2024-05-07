package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.service.CandidateService;
import com.vote.onlinevotingsystem.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vote")
public class VoteController {

    private final PositionService positionService;
    private final CandidateService candidateService;

    public VoteController(PositionService positionService,
                          CandidateService candidateService) {
        this.positionService = positionService;
        this.candidateService = candidateService;
    }

    @GetMapping
    public String vote() {
        return "vote";
    }

    @GetMapping("/positions")
    public String getPosition(Model model) {
        List<String> positions = positionService.getPositions();

        model.addAttribute("positions", positions);

        return "redirect:/vote";
    }

    @GetMapping("/candidates")
    public String getCandidates(String position, Model model) {
        List<String> candidates = candidateService.getCandidatesNames(position);

        model.addAttribute("candidates", candidates);

        return "redirect:/vote";
    }

    @PostMapping("/candidate")
    public String vote(String candidate) {

        //TODO

        return "redirect:/vote";
    }
}

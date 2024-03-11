package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.model.dto.CandidateDTO;
import com.vote.onlinevotingsystem.service.CandidateService;
import com.vote.onlinevotingsystem.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/poll-results")
public class ResultsController {

    private final CandidateService candidateService;
    private final PositionService positionService;

    public ResultsController(CandidateService candidateService,
                             PositionService positionService) {

        this.candidateService = candidateService;
        this.positionService = positionService;
    }

    @GetMapping
    public String results() {
        return "poll-results";
    }

    @GetMapping("/{position}")
    public String results(Model model,
                          @PathVariable String position) {

        List<CandidateDTO> candidates = candidateService.getCandidates(position);
        Integer totalVotes = positionService.getTotalVotes(candidates);

        model.addAttribute("candidates", candidates);
        model.addAttribute("votes", totalVotes);

        return "redirect:/poll-results";
    }
}

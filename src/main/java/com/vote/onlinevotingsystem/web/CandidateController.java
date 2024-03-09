package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.model.dto.CandidateAddDTO;
import com.vote.onlinevotingsystem.service.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public String candidates() {
        return "candidates";
    }

    @PostMapping("/add")
    public String addCandidate(CandidateAddDTO candidateAddDTO) {
        candidateService.add(candidateAddDTO);

        return "redirect:/candidates";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        candidateService.delete(id);

        return "redirect:/candidates";
    }
}

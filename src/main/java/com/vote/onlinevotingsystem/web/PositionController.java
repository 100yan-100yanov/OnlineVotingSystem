package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.model.dto.PositionAddDTO;
import com.vote.onlinevotingsystem.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public String positions() {
        return "positions";
    }

    @PostMapping("/add")
    public String add(PositionAddDTO positionAddDTO) {
        positionService.add(positionAddDTO);

        return "redirect:/positions";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        positionService.delete(id);

        return "redirect:/positions";
    }
}

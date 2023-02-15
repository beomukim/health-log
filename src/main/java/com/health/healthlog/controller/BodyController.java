package com.health.healthlog.controller;

import com.health.healthlog.domain.Body;
import com.health.healthlog.dto.security.BoardPrincipal;
import com.health.healthlog.service.BodyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/body")
@Controller
public class BodyController {

    private final BodyService bodyService;

    @GetMapping
    public String getAllBody(String userId, ModelMap map, @AuthenticationPrincipal BoardPrincipal boardPrincipal) {
        List<Body> bodies = bodyService.searchBodies(boardPrincipal.toDto());
        map.addAttribute("bodies", bodies);
        return "body/index";
    }
}

package com.health.healthlog.controller;

import com.health.healthlog.domain.Body;
import com.health.healthlog.repository.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BodyController {
    @Autowired
    private BodyRepository bodyRepository;

    @GetMapping("/body")
    public @ResponseBody Iterable<Body> getAllBody() {
        return bodyRepository.findAll();
    }
}

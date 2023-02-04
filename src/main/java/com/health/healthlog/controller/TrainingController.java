package com.health.healthlog.controller;

import com.health.healthlog.domain.Training;
import com.health.healthlog.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrainingController {

    @Autowired
    private TrainingRepository trainingRepository;

    @GetMapping("/training")
    public @ResponseBody Iterable<Training> getAllTraining() {
        return trainingRepository.findAll();
    }
}

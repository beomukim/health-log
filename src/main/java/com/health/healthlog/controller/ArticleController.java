package com.health.healthlog.controller;

import com.health.healthlog.repository.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String articles(ModelMap map) {
        map.addAttribute("articles", articleRepository.findAll());
        return "articles/index";
    }
}

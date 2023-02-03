package com.health.healthlog.controller;

import com.health.healthlog.domain.Article;
import com.health.healthlog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public @ResponseBody Iterable<Article> getAllArticle() {
        return articleRepository.findAll();
    }
}

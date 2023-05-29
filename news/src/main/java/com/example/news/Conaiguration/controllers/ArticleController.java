package com.example.news.Conaiguration.controllers;

import com.example.news.Conaiguration.servicies.StartProgramService;
import lombok.RequiredArgsConstructor;
import com.example.news.Conaiguration.models.Article;
import org.springframework.web.bind.annotation.*;
import com.example.news.Conaiguration.servicies.ArticleServise;

import java.util.List;

/**
 Класс Контроллер отвечает за обработку запросов
 */

@RestController
@RequestMapping(value = "", produces = "application/json")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleServise articleServise;
    private final StartProgramService startService;

    @GetMapping("v3/articles/{id:\\d+}")
    public Article findById(@PathVariable Long id) {
        return articleServise.getArticleById(id);
    }

    @GetMapping("v3/articles")
    public List<Article> findAll() {
        return articleServise.findAll();
    }

    @GetMapping("v3/start")
    public void start() {
        startService.start();
    }

    @GetMapping("v3/articles/{newsSite:\\D+}")
    public List<Article> articleId(@RequestParam(value = "newsSite") String newsSite) {
        return articleServise.findArticleByNewsSite(newsSite);
    }
}

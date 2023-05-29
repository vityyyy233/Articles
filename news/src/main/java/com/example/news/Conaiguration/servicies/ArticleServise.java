package com.example.news.Conaiguration.servicies;

import com.example.news.Conaiguration.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.news.Conaiguration.models.Article;
import org.springframework.stereotype.Service;
import com.example.news.Conaiguration.repositories.ArticleRepository;

import java.util.List;

/**
 Сервис для сущности Article
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServise {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Article getArticleById(Long id) {
        var article = articleRepository.findById(id);
        if(article.isPresent()) {
        return article.get();
        } else throw new NotFoundException(id);
    }

    public List<Article> findArticleByNewsSite(String newsSite) {
        var articles = articleRepository.findArticlesByNewsSite(newsSite);
        if (articles.isEmpty()) {
            throw new NotFoundException(newsSite);
        } else return articles;
    }
}

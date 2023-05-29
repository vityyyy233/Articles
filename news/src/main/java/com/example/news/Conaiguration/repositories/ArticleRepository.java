package com.example.news.Conaiguration.repositories;

import com.example.news.Conaiguration.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 Репозиторий для Article
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticlesByNewsSite(String newsSite);

}


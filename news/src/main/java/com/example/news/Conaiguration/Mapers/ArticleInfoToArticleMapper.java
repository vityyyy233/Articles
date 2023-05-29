package com.example.news.Conaiguration.Mapers;

import com.example.news.Conaiguration.models.Article;
import org.springframework.stereotype.Component;
import com.example.news.Conaiguration.pojo.ArticleInfo;
/**
 Класс отвечает за маппинг из ArticleInfo и содержимого статьи в сущность Article
 */

@Component
public class ArticleInfoToArticleMapper {

    public Article articleInfoAndContentToArticleMap(String content, ArticleInfo articleInfo) {
            var article =Article.builder()
                            .title(articleInfo.getTitle())
                            .newsSite(articleInfo.getNewsSite())
                            .publishedAt(articleInfo.getPublishedAt())
                            .article(content)
                            .build();
            return article;

    }
}

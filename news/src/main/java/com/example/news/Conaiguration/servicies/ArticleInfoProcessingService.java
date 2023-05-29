package com.example.news.Conaiguration.servicies;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.news.Conaiguration.pojo.ArticleInfo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 Класс отвечает за обработку скачанных элементов ArticleInfo, фильтрация по запрещенным словам сортировка и группировка
 по полю NewsSite
 */

@Service
public class ArticleInfoProcessingService {
@Value("${black_list_news}")
private List<String> blackWords;

    public List<ArticleInfo> filterByBlackListWords(List<ArticleInfo> articlesInfo) {
        var articlesWithoutBlackWords = new CopyOnWriteArrayList<>(articlesInfo);
        for (String word : blackWords) {
            articlesWithoutBlackWords.stream()
                    .filter(article -> StringUtils.containsIgnoreCase(article.getTitle(), word))
                    .forEach(articlesWithoutBlackWords :: remove);
        }
        return articlesWithoutBlackWords;
    }

    public Map<String, List<ArticleInfo>> sortByPublishedAtAndGroupByNewsSiteListArticle(List<ArticleInfo> articles) {
        var sortAndGroupArticle = articles.stream()
                .sorted(Comparator.comparing(ArticleInfo::getPublishedAt))
                .collect(
                        Collectors.groupingBy(ArticleInfo::getNewsSite));
        return sortAndGroupArticle;
    }
}

package com.example.news.Conaiguration.servicies;

import ai.platon.pulsar.boilerpipe.extractors.ArticleExtractor;
import ai.platon.pulsar.boilerpipe.utils.ProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.example.news.Conaiguration.pojo.ArticleInfo;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Класс отвечает за скачивание ресурсов по URL
 */

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class DownloadService {
    @Value("${DownloadUrl}")
    private String url;
    private final String limitParametr = "?_limit=";
    private final String skipParametr = "&_start=";
    public  List<ArticleInfo> downloadArticlesInfo(int limit, int skip) throws IOException {
        var objectMapper = new ObjectMapper();
        var fullUrl = url + limitParametr + limit + skipParametr + skip;
        List<ArticleInfo> ArticlesInfo = objectMapper.readValue(new URL(fullUrl), new TypeReference<>() {
        });
        log.info("Download {} articlesInfo", ArticlesInfo.size());
        return ArticlesInfo;

    }

    public Map<String, ArticleInfo> downloadContentOfArticles(List<ArticleInfo> articlesInfo) throws IOException, ProcessingException {
        var contentArticleInfoMap = new HashMap<String, ArticleInfo>();
        for (var articleInfo : articlesInfo) {
            var fullUrl = articleInfo.getUrl();
            URL url = new URL(fullUrl);
            String content = ArticleExtractor.INSTANCE.getText(url);
            if(content.isEmpty()){
                content = fullUrl + " not valid";
            }
            contentArticleInfoMap.put(content, articleInfo);
        }
        return contentArticleInfoMap;
    }

}

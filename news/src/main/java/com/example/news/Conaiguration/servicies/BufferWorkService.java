package com.example.news.Conaiguration.servicies;

import ai.platon.pulsar.boilerpipe.utils.ProcessingException;
import com.example.news.Conaiguration.Mapers.ArticleInfoToArticleMapper;
import com.example.news.Conaiguration.pojo.ArticleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 Класс отвечает за работу с общим буффером потоков
 */

@Service
@RequiredArgsConstructor
public class BufferWorkService {
    private final DownloadService downloadService;
    private final ArticleInfoToArticleMapper articleInfoToArticleMapper;
    private final ArticleServise articleServise;
    private ConcurrentHashMap<String, List<ArticleInfo>> newsSiteArticlesInfoMap = new ConcurrentHashMap<>();

     void fromBufferToDB(String key) throws IOException, ProcessingException {
        var newsSiteArticleInfoMap = downloadService.downloadContentOfArticles(newsSiteArticlesInfoMap.get(key));
        for (var entry2 : newsSiteArticleInfoMap.entrySet()) {
            var article = articleInfoToArticleMapper.articleInfoAndContentToArticleMap(entry2.getKey(), entry2.getValue());
            articleServise.save(article);
        }
         newsSiteArticlesInfoMap.remove(key);
    }

     void putOrUpdateValBuffer(Map.Entry<String, List<ArticleInfo>> newsSiteArticlesInfoEntry) {
        if (newsSiteArticlesInfoMap.containsKey(newsSiteArticlesInfoEntry.getKey())) {
            for (var articleInfo : newsSiteArticlesInfoEntry.getValue()) {
                newsSiteArticlesInfoMap.get(newsSiteArticlesInfoEntry.getKey()).add(articleInfo);
            }
        } else newsSiteArticlesInfoMap.put(newsSiteArticlesInfoEntry.getKey(), newsSiteArticlesInfoEntry.getValue());
    }

     ConcurrentHashMap<String, List<ArticleInfo>> getArticleInfoBuffer () {
        return newsSiteArticlesInfoMap;
    }
}

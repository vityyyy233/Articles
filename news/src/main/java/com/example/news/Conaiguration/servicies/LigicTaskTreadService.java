package com.example.news.Conaiguration.servicies;

import ai.platon.pulsar.boilerpipe.utils.ProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 Класс отвечает за построение основной локиги приложения
 */

@Service
@RequiredArgsConstructor
public class LigicTaskTreadService {

    private final DownloadService downloadService;
    private final ArticleInfoProcessingService articleInfoProcessingService;
    private final BufferWorkService bufferWorkService;

   private final ReentrantLock lock = new ReentrantLock();

    @Value("${total_number_of_records_for_newssite_buffer_limit}")
    private int totalRecordsOnBufferForNewsSiteLimit;

    public void taskTread(int countRecordsToDownload, int skip) throws IOException, ProcessingException {
        var downloadArticlesInfo = downloadService.downloadArticlesInfo(countRecordsToDownload, skip);
        var filterBlackWordsArticlesInfo = articleInfoProcessingService.filterByBlackListWords(downloadArticlesInfo);
        var sortAndGroupByNewsSiteArticlesInfo = articleInfoProcessingService.sortByPublishedAtAndGroupByNewsSiteListArticle(filterBlackWordsArticlesInfo);
        lock.lock();//следующий кусок кода при плотном взаимодействии с мапой не получается раскидать на параллельную работу, CuncurrentHashMap и CopyOnWriteArrayList, Loks не помогли((
        for (var entry : sortAndGroupByNewsSiteArticlesInfo.entrySet()) {
            bufferWorkService.putOrUpdateValBuffer(entry);
                if (bufferWorkService.getArticleInfoBuffer().get(entry.getKey()).size() >= totalRecordsOnBufferForNewsSiteLimit) {
                    bufferWorkService.fromBufferToDB(entry.getKey());
                }
        }
        lock.unlock();
    }
}


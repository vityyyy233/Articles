package com.example.news.Conaiguration.servicies;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 Класс отвечает за логику при при запуске приложения(создание потоков, отправка в них задач)
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StartProgramService {

    private final LigicTaskTreadService ligicTaskTreadServiceService;
    private final BufferWorkService bufferWorkService;

    @Value("${pool_treads_size_limit}")
    private  int poolSize;
    @Value("${articles_limit}")
    private  int articlesLimit;
    @Value("${articles_skipped}")
    private  int articlesSkip;
    @Value("${cycle_total_records_one_tread_limit}")
    private  int recordsOneCycleTread;
    @Value("${records_treads_limit}")
    private  int limitRecordsTreads;

    public void start () {
        System.out.println("start");
        try {
            var limitRecords = limitRecordsTreads <= articlesLimit ? limitRecordsTreads : articlesLimit;
            ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
            var skipPlusLimit = articlesSkip + limitRecords;
            for (int i = articlesSkip; i < (skipPlusLimit); i += recordsOneCycleTread) {
                if(skipPlusLimit - i >= recordsOneCycleTread) {
                    executorService.execute(new MyThread(recordsOneCycleTread, i));
                } else executorService.execute(new MyThread(skipPlusLimit - i, i));
            }
            executorService.shutdown();
            executorService.awaitTermination(10L, TimeUnit.MINUTES);
            var newsSiteArticlesInfoMap = bufferWorkService.getArticleInfoBuffer();
            ExecutorService executorService2 = Executors.newFixedThreadPool(poolSize);
            for (var entry : newsSiteArticlesInfoMap.entrySet()) {
                executorService2.execute(new MyThread2(entry.getKey()));
            }
            executorService2.shutdown();
            executorService2.awaitTermination(10L, TimeUnit.MINUTES);
        }catch(Throwable e){
                e.getStackTrace();
        }
        System.out.println("end");
    }


    class MyThread implements Runnable {

        private int countRecords;
        private int skipRecords;

        public MyThread(int countRecords, int skipRecords) {
            this.countRecords = countRecords;
            this.skipRecords = skipRecords;
        }

        @Override
        public void run() {
                try {
                    ligicTaskTreadServiceService.taskTread(countRecords, skipRecords);
                } catch (Throwable e) {
                    e.getStackTrace();
                }
        }
    }

        class MyThread2 implements Runnable {

            private String keyMap;

            public MyThread2(String key) {
                keyMap = key;
            }

            @Override
            public void run() {
                    try {
                        bufferWorkService.fromBufferToDB(keyMap);
                    } catch (Throwable e) {
                        e.getStackTrace();
                    }
            }
        }
    }


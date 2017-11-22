package com.mist.cloudtestingplatform.task;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import com.mist.cloudtestingplatform.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Prophet on 2017/11/22.
 */
@Component
public class DataPersistTaskImpl implements DataPersistTask {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    private BlockingQueue<Data> blockingQueue;

    private ExecutorService executorService;

    private static final int CONSUMER_COUNT = 3;

    @PostConstruct
    public void init() {

        blockingQueue = new LinkedBlockingDeque<>();

        executorService = Executors.newFixedThreadPool(CONSUMER_COUNT);

        for (int i = 0; i < CONSUMER_COUNT - 1; i++) {
            executorService.submit(new DataPersistBatchConsumer(dataService, blockingQueue));
        }

        executorService.submit(new DataPersistTimeoutConsumer(dataService, blockingQueue));

    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }


    // producer
    @Override
    public boolean persistData(Data data) {
        return blockingQueue.offer(data);
    }
}

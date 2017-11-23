package com.mist.cloudtestingplatform.task;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import com.mist.cloudtestingplatform.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
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

    private List<DataPersistConsumer> consumerList;

    @PostConstruct
    public void init() {

        blockingQueue = new LinkedBlockingDeque<>();

        executorService = Executors.newFixedThreadPool(CONSUMER_COUNT);

        consumerList = new ArrayList<>(CONSUMER_COUNT);

        for (int i = 0; i < CONSUMER_COUNT - 1; i++) {
            DataPersistConsumer consumer = new DataPersistBatchConsumer(dataService, blockingQueue);
            consumerList.add(consumer);
            executorService.submit(consumer);
        }

        DataPersistConsumer consumer = new DataPersistTimeoutConsumer(dataService, blockingQueue);
        consumerList.add(consumer);
        executorService.submit(consumer);

    }

    @PreDestroy
    public void destroy() {

        for(DataPersistConsumer consumer: consumerList) {
            consumer.stop();
        }

        executorService.shutdown();
    }


    // producer
    @Override
    public boolean persistData(Data data) {
        return blockingQueue.offer(data);
    }
}

package com.mist.cloudtestingplatform.task;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Prophet on 2017/11/22.
 */
public class DataPersistBatchConsumer implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(DataPersistBatchConsumer.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private volatile boolean isRunning = true;

    private DataService dataService;

    private BlockingQueue<Data> blockingQueue;

    private int consumerCounter;

    private int batchSize = 10;

    private long batchInterval = 1000L;

    public DataPersistBatchConsumer(DataService dataService, BlockingQueue<Data> blockingQueue) {

        this.dataService = dataService;
        this.blockingQueue = blockingQueue;

        this.consumerCounter = atomicInteger.incrementAndGet();

    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setBatchInterval(long batchInterval) {
        this.batchInterval = batchInterval;
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {

        logger.info("data persist batch consumer {} is running...", consumerCounter);

        try {

            while (isRunning) {

                if (blockingQueue.size() >= batchSize) {
                    synchronized (this) {
                        if (blockingQueue.size() >= batchSize) {

                            List<Data> datas = new ArrayList<>(batchSize);
                            blockingQueue.drainTo(datas, batchSize);

                            try {
                                dataService.saveDataCollection(datas);
                            } catch (Exception e) {
                                logger.warn("persist data fail by batch consumer: {}, exception: {}"
                                        , consumerCounter, e);
                            }

                            logger.info("persist data by batch consumer: {}, threadId: {}"
                                    , consumerCounter, Thread.currentThread().getId());
                        }
                    }
                }

                Thread.sleep(batchInterval);

            }

        } catch (InterruptedException e) {
            logger.warn("batch consumer {} interrupted, cause: {}", consumerCounter, e);
            // restore interrupt status
            Thread.currentThread().interrupt();
        } finally {
            logger.info("batch consumer {} exit", consumerCounter);
        }

    }
}

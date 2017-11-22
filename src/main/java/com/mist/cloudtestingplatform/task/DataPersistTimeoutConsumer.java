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
public class DataPersistTimeoutConsumer implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(DataPersistTimeoutConsumer.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private volatile boolean isRunning = true;

    private DataService dataService;

    private BlockingQueue<Data> blockingQueue;

    private int consumerCounter;

    private int batchSize = 10;

    private long batchInterval = 1000L;

    private long timeout = 2000L;

    public DataPersistTimeoutConsumer(DataService dataService, BlockingQueue<Data> blockingQueue) {

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

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {

        logger.info("data persist timeout consumer {} is running...", consumerCounter);

        try {

            while (isRunning) {

                if (blockingQueue.size() > 0 && blockingQueue.size() < batchSize) {
                    Data data = blockingQueue.peek();
                    if (data != null
                            && System.currentTimeMillis() - data.getTimestamp() > timeout) {

                        List<Data> datas = new ArrayList<>(batchSize);
                        blockingQueue.drainTo(datas, batchSize);

                        try {
                            dataService.saveDataCollection(datas);
                        } catch (Exception e) {
                            logger.warn("persist data fail by timeout consumer: {}, exception: {}"
                                    , consumerCounter, e);
                        }
                    }

                }

                Thread.sleep(batchInterval);
            }

        } catch (InterruptedException e) {
            logger.warn("timeout consumer {} interrupted, cause: {}", consumerCounter, e);
            // restore interrupt status
            Thread.currentThread().interrupt();
        } finally {
            logger.info("timeout consumer {} exit", consumerCounter);
        }
    }
}

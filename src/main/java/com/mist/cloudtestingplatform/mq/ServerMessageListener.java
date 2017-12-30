package com.mist.cloudtestingplatform.mq;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.task.DataPersistTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Message;

/**
 * Created by Prophet on 2017/12/30.
 */
@Component
@EnableJms
public class ServerMessageListener {

    private Logger logger = LoggerFactory.getLogger(ServerMessageListener.class);

    private DataPersistTask dataPersistTask;

    @Autowired
    public void setDataPersistTask(DataPersistTask dataPersistTask) {
        this.dataPersistTask = dataPersistTask;
    }

    @JmsListener(containerFactory = "jmsListenerContainerFactory", destination = "messenger.topic.server.*")
    public void onMessage(Message message) {

        long startTime = System.currentTimeMillis();

        try {
            Destination destination = message.getJMSDestination();
            logger.info("destination" + destination);

            Data data = MessageProcessor.dataFromMessage(message, true);

            dataPersistTask.persistData(data);

        } catch (Exception e) {
            logger.warn(e.toString());
            e.printStackTrace();
        }

        logger.info("on message process time: {} ms", System.currentTimeMillis() - startTime);

    }
}

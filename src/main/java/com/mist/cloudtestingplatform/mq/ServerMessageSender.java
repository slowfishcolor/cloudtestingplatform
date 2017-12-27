package com.mist.cloudtestingplatform.mq;

import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.util.JsonUtil;
import com.mist.cloudtestingplatform.task.DataPersistTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Prophet on 2017/11/30.
 */
@Component
public class ServerMessageSender implements MessageSender{

    Logger logger = LoggerFactory.getLogger(ServerMessageSender.class);

    JmsTemplate jmsTemplate;

    @Qualifier("jmsTopicTemplate")
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public boolean sendMessage(String deviceId, String message) {
        try {
            jmsTemplate.convertAndSend(publishDestination + deviceId, message);
            return true;
        } catch (Exception e) {
            logger.warn(e.toString());
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean sendMessage(Payload payload) {
        try {
            String destinationString = publishDestination + payload.getDeviceId();
            payload.setDestination(destinationString);
            String messageString = JsonUtil.payLoadToJson(payload);
            jmsTemplate.convertAndSend(destinationString, messageString);
            return true;
        } catch (Exception e) {
            logger.warn(e.toString());
            e.printStackTrace();
        }
        return false;
    }
}

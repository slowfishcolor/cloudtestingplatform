package com.mist.cloudtestingplatform.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by Prophet on 2017/11/14.
 */
@Component
@EnableJms
public class DeviceMessageListener {

    private Logger logger = LoggerFactory.getLogger(DeviceMessageListener.class);

    @JmsListener(containerFactory = "jmsListenerContainerFactory", destination = "messenger.topic.device.*")
    public void onMessage(Message message) {
        logger.info("new msg");
        try {
            Destination destination = message.getJMSDestination();
            logger.info("destination" + destination);
            String messageStr;

            if (message instanceof TextMessage) {

                logger.info("text message");
                TextMessage textMessage = (TextMessage) message;
                messageStr = textMessage.getText();

            } else if (message instanceof BytesMessage) {

                logger.info("byte message");
                BytesMessage bytesMessage = (BytesMessage) message;
                StringBuilder stringBuilder  = new StringBuilder();
                byte[] b = new byte[1024];
                int len = -1;

                while((len = bytesMessage.readBytes(b))!=-1){
                    stringBuilder.append(new String(b, 0, len));
                }

                messageStr = stringBuilder.toString();
            } else {
                messageStr = "unsupported message type";
            }

            logger.info(messageStr);
        } catch (Exception e) {
            logger.warn(e.toString());
            e.printStackTrace();
        }


    }

}

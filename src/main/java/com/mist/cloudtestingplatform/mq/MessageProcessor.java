package com.mist.cloudtestingplatform.mq;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.model.DataFacotry;
import com.mist.cloudtestingplatform.protocol.model.PayloadBase;
import com.mist.cloudtestingplatform.protocol.util.JsonUtil;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by Prophet on 2017/12/30.
 */
public class MessageProcessor {

    static Data dataFromMessage(Message message, boolean isServer) throws JMSException, IOException {

        String messageStr;

        if (message instanceof TextMessage) {

//                logger.info("text message");
            TextMessage textMessage = (TextMessage) message;
            messageStr = textMessage.getText();

        } else if (message instanceof BytesMessage) {

//                logger.info("byte message");
            BytesMessage bytesMessage = (BytesMessage) message;
            StringBuilder stringBuilder = new StringBuilder();
            byte[] b = new byte[1024];
            int len = -1;

            while ((len = bytesMessage.readBytes(b)) != -1) {
                stringBuilder.append(new String(b, 0, len));
            }

            messageStr = stringBuilder.toString();
        } else {
            messageStr = "unsupported message type";
        }

//            logger.info(messageStr);

        PayloadBase payload = JsonUtil.jsonToPayloadBase(messageStr);
        Data data;
        if (isServer) {
            data = DataFacotry.createServerData(payload, messageStr);
        } else {
            data = DataFacotry.createDeviceData(payload, messageStr);
        }
        data.setMessageId(payload.getMessageId());
        return data;
    }
}

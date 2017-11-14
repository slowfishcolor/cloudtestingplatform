package com.mist.cloudtestingplatform.mq;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;

/**
 * Created by Prophet on 2017/11/14.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MQTest {

    @Autowired
    private JmsTemplate jmsTopicTemplate;

    @Test
    public void sendMessageTest() {
        Destination destination = new ActiveMQTopic("messenger.topic.server");
        jmsTopicTemplate.send(destination, new MessageCreator() {
            // 消息的产生，返回消息发送消息
            public Message createMessage(Session s) throws JMSException {
                TextMessage msg = s
                        .createTextMessage("Spring send msg ----> Hello activeMQ4");
                return msg;
            }
        });
    }

}

package com.mist.cloudtestingplatform.mq;

import com.mist.cloudtestingplatform.protocol.model.Payload;

/**
 * Created by Prophet on 2017/11/30.
 */
public interface MessageSender {

    public static final String publishDestination = "messenger.topic.server.";

    public boolean sendMessage(String deviceId, String message);

    public boolean sendMessage(Payload payload);
}

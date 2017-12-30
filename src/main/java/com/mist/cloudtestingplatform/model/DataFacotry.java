package com.mist.cloudtestingplatform.model;

import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.model.PayloadBase;
import com.mist.cloudtestingplatform.protocol.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Prophet on 2017/11/30.
 */
public class DataFacotry {

    static Logger logger = LoggerFactory.getLogger(DataFacotry.class);

    public static Data createServerData(Payload payload) {
        return createData(payload, 1);
    }

    public static Data createDeviceData(Payload payload) {
        return createData(payload, 0);
    }

    public static Data createData(Payload payload, int direction) {
        try {
            Data data = new Data();
            data.setDeviceId(payload.getDeviceId());
            data.setPhysicalDeviceId(payload.getPhysicalDeviceId());
            data.setDirection(direction);
            data.setUserId(payload.getUserId());
            data.setTimestamp(System.currentTimeMillis());
            data.setData(JsonUtil.payLoadToJson(payload));
            return data;
        } catch (Exception e) {
            logger.warn(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static Data createDeviceData(PayloadBase payloadBase, String messageStr) {
        return createData(payloadBase, messageStr, 0);
    }

    public static Data createServerData(PayloadBase payloadBase, String messageStr) {
        return createData(payloadBase, messageStr, 1);
    }

    public static Data createData(PayloadBase payloadBase, String messageStr, int direction) {
        Data data = new Data();
        data.setDeviceId(payloadBase.getDeviceId());
        data.setPhysicalDeviceId(payloadBase.getPhysicalDeviceId());
        data.setDirection(direction);
        data.setUserId(payloadBase.getUserId());
        data.setTimestamp(System.currentTimeMillis());
        data.setData(messageStr);
        return data;
    }
}

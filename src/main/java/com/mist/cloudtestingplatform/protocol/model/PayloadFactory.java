package com.mist.cloudtestingplatform.protocol.model;

import com.mist.cloudtestingplatform.protocol.config.StatusCode;

/**
 * Created by Prophet on 2017/11/23.
 */
public class PayloadFactory {

    public static Payload createPayload(Option option, Data data) {

        Payload payload = new Payload();

        payload.setType(data.getType());
        payload.setOption(option);
        payload.setData(data);
        payload.setCode(StatusCode.SUCCESS);
        payload.setTimestamp(System.currentTimeMillis());

        return payload;
    }

}

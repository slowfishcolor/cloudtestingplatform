package com.mist.cloudtestingplatform.protocol.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mist.cloudtestingplatform.protocol.model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Prophet on 2017/11/23.
 */
public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String payLoadToJson(Payload payLoad) throws JsonProcessingException {
        return objectMapper.writeValueAsString(payLoad);
    }

    public static PayloadBase jsonToPayloadBase(String jsonStr) throws IOException {
        return objectMapper.readValue(jsonStr, PayloadBase.class);
    }

    public static Payload jsonToPayload(String jsonStr) throws IOException {

        Payload payload = new Payload();

        JsonNode node = objectMapper.readTree(jsonStr);

        payload.setMessageId(node.get("messageId").asLong());
        payload.setDestination(node.get("destination").asText());
        payload.setDeviceId(node.get("deviceId").asText());
        payload.setTimestamp(node.get("timestamp").asLong());
        payload.setUserId(node.get("userId").asInt());
        payload.setCode(node.get("code").asInt());
        payload.setType(node.get("type").asText());

        String optionStr = node.get("option").toString();
        payload.setOption(objectMapper.readValue(optionStr, Option.class));

        String dataStr = node.get("data").toString();
        if (Payload.ANALOG_SAMPLE_DATA.equals(payload.getType())) {
            payload.setData(objectMapper.readValue(dataStr, AnalogSampleData.class));
        } else if (Payload.CONTROL_DATA.equals(payload.getType())) {
            payload.setData(objectMapper.readValue(dataStr, ControlData.class));
        } else if (Payload.INSTRUCTION_DATA.equals(payload.getType())) {
            payload.setData(objectMapper.readValue(dataStr, InstructionData.class));
        } else {
            payload.setData(null);
        }

        return payload;
    }

    public static void main(String[] args) throws Exception {

        Option option = new Option();
        AnalogSampleData data = new AnalogSampleData();
        data.setPort("a1");
        data.setMethod("normal");
        data.setFrequency(100.0);
        data.setSampleCount(100);
        data.setTimestamp(1000L);

        double[] value = {1.0, 2.0};
        data.setValue(value);

        Payload payLoad = new Payload();

        payLoad.setDeviceId("deviceId");
        payLoad.setTimestamp(1000L);
        payLoad.setType("AnalogSampleData");
        payLoad.setOption(option);
        payLoad.setData(data);

        String jsonStr = payLoadToJson(payLoad);

        System.out.println(jsonStr);

        PayloadBase jsonPayloadBase = jsonToPayloadBase(jsonStr);

        Payload jsonPayload = jsonToPayload(jsonStr);

        System.out.println(jsonPayload.getDestination());
        System.out.println(jsonPayload.getMessageId());
        System.out.println(jsonPayload.getOption());
        System.out.println(jsonPayload.getData());

        Instruction instruction = new Instruction();
        instruction.setName("test");
        instruction.setType(Instruction.COMMAND_STRING);
        InstructionData instructionData = new InstructionData(instruction);

        payLoad.setType(Payload.INSTRUCTION_DATA);
        payLoad.setData(instructionData);

        jsonStr = payLoadToJson(payLoad);
        jsonPayload = jsonToPayload(jsonStr);
        System.out.println(jsonPayload.getData());

    }

}

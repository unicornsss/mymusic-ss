package com.music.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: denglong
 * Date: 2018/9/17
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {
    private static JsonUtil jsonUtil;
    private static ObjectMapper objectMapper;
    // private修饰的构造函数，出了这个类之外，没有其他类能够调用
    private JsonUtil() {}

    public static JsonUtil getJsonUtil() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public String obj2Json(Object obj) throws JsonProcessingException {
        getObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public Object json2Obj(String json, Class<?> clazz) throws IOException {
        getObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}

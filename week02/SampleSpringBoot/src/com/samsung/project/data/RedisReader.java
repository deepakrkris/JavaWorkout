package com.samsung.project.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.redislabs.modules.rejson.JReJSON;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

class RedisReader {
    private Logger log = Logger.getLogger(String.valueOf(getClass()));
    private final Jedis jedis;
    private final JReJSON jrejson;

    public RedisReader() {
        this.jedis = new Jedis("redis://localhost:6379");
        this.jrejson = new JReJSON(this.jedis);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        RedisReader reader = new RedisReader();
        System.out.println(reader.jrejson.get("6", String.class));
    }
}

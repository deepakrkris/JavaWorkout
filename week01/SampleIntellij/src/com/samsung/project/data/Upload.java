package com.samsung.project.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.mockito.internal.matchers.Null;
import redis.clients.jedis.Jedis;
import com.redislabs.modules.rejson.JReJSON;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

class Root{
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;
}

class JsonFileReader {
    private Logger log = Logger.getLogger(String.valueOf(getClass()));
    private final ObjectMapper objectMapper;
    private final Jedis jedis;
    private final JReJSON jrejson;

    public JsonFileReader() {
        this.objectMapper = new ObjectMapper();
        this.jedis = new Jedis("redis://localhost:6379");
        this.jrejson = new JReJSON(this.jedis);
    }

    public List<Object> parseJsonFile(String fileName, Class clazz) throws IOException, URISyntaxException {
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println(classLoader.getResource(fileName) != null);
            File file = new File(classLoader.getResource(fileName).toURI());
            return objectMapper.readValue(file, typeFactory.constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.info("error occurred " + e.getMessage());
            throw e;
        }
    }

    public void deleteKey(String key) {
        jedis.del(key);
    }

    public void saveData(String key, Object value) throws JsonProcessingException {
        String jsonValue = objectMapper.writeValueAsString(value);
        jrejson.set(key, jsonValue);
    }
}

public class Upload {
    private static Logger log = Logger.getLogger(String.valueOf(Upload.class));

    public static void main(String[] args) throws IOException, URISyntaxException {
        JsonFileReader jsonFileReader = new JsonFileReader();
        List<Object> rows = jsonFileReader.parseJsonFile("data.json", Root.class);
        for (Object obj : rows) {
            Root root = (Root)obj;
            log.info(root.category);
            jsonFileReader.deleteKey("" + root.id);
            jsonFileReader.saveData("" + root.id, root);
        }
        log.info(">>>> upload success !");
    }
}

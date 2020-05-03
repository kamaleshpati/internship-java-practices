package com.cognitree.yamljsonparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    public Map<String, Object> yamlToMap(String fileName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);
        return yaml.load(inputStream);
    }

    public String jsonToString(String fileName) {
        JSONObject json;
        InputStream inputStream;
        try {
            System.out.println("Looking for " + fileName);
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(inputStream));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                JSONParser parser = new JSONParser();
                json = (JSONObject) parser.parse(String.valueOf(responseStrBuilder));
                log.debug("jsonToString : {}", json);
                return responseStrBuilder.toString();
            } else {
                throw new FileNotFoundException("JSON file '" + fileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            log.error("jasonToString : {}", e.getMessage());
        }
        return null;
    }

    public HashMap<String, Object> jsonToMap(String fileName) throws IOException {
        HashMap<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        String res=jsonToString(fileName);
//        myMap = objectMapper.readValue(jsonStream, HashMap.class);
//        System.out.println("Map is: "+myMap);
        map = objectMapper.readValue(String.valueOf(res), new TypeReference<HashMap<String, Object>>() {
        });
        return map;
    }
}

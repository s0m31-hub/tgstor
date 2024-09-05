package org.nwolfhub.tgstor;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ConfigParser {
    private HashMap<String, String> config = new HashMap<>();


    private void parseConfig(String plain) {
        String[] split = plain.split("\n");
        for(String line:split) {
            String key = line.split("=")[0].strip();
            String value = line.substring(line.indexOf("=")+1);
            config.put(key, value);
        }
    }
}

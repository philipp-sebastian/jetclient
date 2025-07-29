package dev.jetclient.config;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigFile {
    private final File configFile;
    private final Map<String, String> configMap = new HashMap<>();

    public ConfigFile(String path) {
        this.configFile = new File(path);
        createConfigFile();
        loadConfig();
    }

    private void createConfigFile() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void loadConfig() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile))) {
            String line;
            String currentSection = "";

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1).trim();
                    continue;
                }

                String[] parts = line.split("=", 2);
                if (parts.length == 2 && !currentSection.isEmpty()) {
                    configMap.put(currentSection + "_" + parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void saveConfig() {
        Map<String, Map<String, String>> groupedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            String[] parts = entry.getKey().split("_", 2);
            if (parts.length == 2) {
                groupedMap
                        .computeIfAbsent(parts[0], k -> new LinkedHashMap<>())
                        .put(parts[1], entry.getValue());
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configFile))) {
            for (Map.Entry<String, Map<String, String>> module : groupedMap.entrySet()) {
                bufferedWriter.write("[" + module.getKey() + "]");
                bufferedWriter.newLine();
                for (Map.Entry<String, String> setting : module.getValue().entrySet()) {
                    bufferedWriter.write(setting.getKey() + "=" + setting.getValue());
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public String getValue(String key) {
        return configMap.get(key);
    }

    public void setValue(String key, String value) {
        configMap.put(key, value);
    }
}

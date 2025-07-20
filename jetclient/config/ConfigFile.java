package dev.jetclient.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigFile {
    private final File configFile;

    public ConfigFile(String path) {
        this.configFile = new File(path);
        createConfigFile();
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

    public void setValue(String key, String value) {
        List<String> lines = new ArrayList<>();
        try {
            /* Replace existing key with new value or create a new key-value pair */
            BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(key + "=")) {
                    lines.add(line);
                }
            }
            lines.add(key + "=" + value);
            bufferedReader.close();

            /* Write changes back in configfile */
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configFile));
            for (String entry : lines) {
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getValue(String key) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(key + "=")) {
                    String[] lineParts = line.split("=");
                    if (lineParts.length > 1) {
                        return lineParts[1];
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}

package org.example.file.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesUsage {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("admin", "value");
        properties.setProperty("user", "value");

        properties.store(
                new PrintWriter("/IdeaProjects/basic-learning/basic/src/main/resources/Properties.properties", StandardCharsets.UTF_8),
                "增加一个注释");
        properties.load(new FileInputStream("/IdeaProjects/basic-learning/basic/src/main/resources/Properties.properties"));
        System.out.println(properties);
        System.out.println(properties.getProperty("admin"));
    }
}

package utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Properties;

public class PropertiesDB {

    public static Properties loadProperties(String filename) {
        Properties dbProps = new Properties();

        try(FileInputStream input = new FileInputStream(filename)) {
            dbProps.load(input);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return dbProps;
    }
}

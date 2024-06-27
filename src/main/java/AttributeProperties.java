
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class AttributeProperties {
     private Properties properties;

    public AttributeProperties(String propertiesFilePath) throws IOException {
        properties = new Properties();
        FileInputStream inputStream = new FileInputStream(propertiesFilePath);
        properties.load(inputStream);
        inputStream.close();
    }

    public Double getLowerBound(String attributeName) {
        String key = attributeName + ".lowerBound";
        return properties.containsKey(key) ? Double.valueOf(properties.getProperty(key)) : null;
    }

    public Double getUpperBound(String attributeName) {
        String key = attributeName + ".upperBound";
        return properties.containsKey(key) ? Double.valueOf(properties.getProperty(key)) : null;
    }

    public List<Double> getInvalidValues(String attributeName) {
        String key = attributeName + ".invalidValues";
        return properties.containsKey(key) ? 
                Arrays.asList(properties.getProperty(key).split(","))
                .stream().map(Double::valueOf).toList() : null;
    }

    public List<String> getAllowedValues(String attributeName) {
        String key = attributeName + ".allowedValues";
        return properties.containsKey(key) ? 
                Arrays.asList(properties.getProperty(key).split(",")) : null;
    }
    public List<String> getContainingValues(String attributeName) {
        String key = attributeName + ".contains";
        return properties.containsKey(key) ? 
                Arrays.asList(properties.getProperty(key).split(",")) : null;
    }
 
}

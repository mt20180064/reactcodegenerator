
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

    public static void main(String[] args) {
        try {
            AttributeProperties attributeProperties = new AttributeProperties("attributes.properties");

            // Primeri koriš?enja
            System.out.println("numericalAttribute1.lowerBound: " + attributeProperties.getLowerBound("numericalAttribute1"));
            System.out.println("numericalAttribute1.upperBound: " + attributeProperties.getUpperBound("numericalAttribute1"));
            System.out.println("numericalAttribute1.invalidValues: " + attributeProperties.getInvalidValues("numericalAttribute1"));

            System.out.println("textualAttribute1.allowedValues: " + attributeProperties.getAllowedValues("textualAttribute1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

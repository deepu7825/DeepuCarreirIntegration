package com.logisticscloudco.app.Database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Headers.Builder;

public class ExternalHeaders {
    private Properties props;

    public ExternalHeaders() {
        this.props = loadProperties();
    }

private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ExternalHeaders.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("config.properties is not found");
                return properties;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Could not load config.properties");
            e.printStackTrace();
        }
        return properties;
    }
    
                                                
                                                
                                              

public static Headers starShipit(){
    Builder headersBuilder = new Builder();
    headersBuilder.add("Content-Type", "application/json");
    try {
        Properties props = loadProperties();
        headersBuilder.add("StarShipIT-Api-Key", props.getProperty("starshipit.api.key"));
        headersBuilder.add("Ocp-Apim-Subscription-Key", props.getProperty("starshipit.api.secret"));
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        
    Headers headers = headersBuilder.build();
    return headers;
}
}

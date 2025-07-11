package com.logisticscloudco.app.PostLabel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logisticscloudco.app.Database.DatabaseConnection;
import com.logisticscloudco.app.Database.ExternalHeaders;
import com.logisticscloudco.app.Labels.BulkLabelsStarShipIt;
import com.logisticscloudco.app.Labels.LabelStarShipIt;
import com.logisticscloudco.app.Orders.NewOrderStarShipIt;
import com.logisticscloudco.app.Orders.Starshipit.Destination;
import com.logisticscloudco.app.Orders.Starshipit.Orders;
import com.logisticscloudco.app.Orders.Starshipit.SenderDetails;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class PostLabel {
    Headers headers;
    String order_id;
    String order_number;
    String carrier;
    String carrier_service_code;
    String weight;
    String height;
    String width;
    String length;
    String packaging_type;



    public PostLabel(Map<String,String> dbData, Headers headers) {
        this.headers = headers;
        this.order_id = dbData.get("order_id");
        this.order_number = dbData.get("order_number"); 
        this.carrier = dbData.get("carrier_code");
        this.carrier_service_code = dbData.get("service_code");
        this.weight = dbData.get("package_total_weight");
        this.height = dbData.get("package_hight");
        this.width = dbData.get("package_width");
        this.length = dbData.get("item_length");
        this.packaging_type = dbData.get("package_packing_instruction");
    
    } 
    public void PostLabels(){
        //Populate 
    }


    public void PostStarShipIt(Map<String,String> dbData) {
            LabelStarShipIt starShipIt = new LabelStarShipIt(order_id,
                        order_number,
                        carrier, 
                        carrier_service_code, 
                        weight, 
                        height, 
                        width,
                        length,
                        packaging_type);
    }

    public void addHeader(String key, String value) {
        headers = headers.newBuilder().add(key, value).build();
    }


    public static void main(String[] args) {
        String QUERY_HEADER = "SELECT * FROM [dbo].[t_cus_itf_ob_ship_labels_header]"; // TODO: Replace with actual query
        String QUERY_DETAILS = "SELECT * FROM [dbo].[t_cus_itf_ob_ship_labels_details]"; // TODO: Replace with actual query
        String StarShipitLabels = "UPDATE d\r\n" + //
                        "SET itf_status = 'P' \r\n" + //
                        "OUTPUT \r\n" + //
                        "    h.start_date,  \r\n" + //
                        "    h.order_id,  \r\n" + //
                        "    h.connote_number,  \r\n" + //
                        "    h.ship_from_name,  \r\n" + //
                        "    h.ship_from_phone,  \r\n" + //
                        "    h.ship_from_supurb,  \r\n" + //
                        "    h.ship_from_state,  \r\n" + //
                        "    h.ship_from_zip,  \r\n" + //
                        "    h.ship_from_country,    \r\n" + //
                        "    h.ship_to_name,  \r\n" + //
                        "    h.ship_to_phone,  \r\n" + //
                        "    h.ship_to_suburb,  \r\n" + //
                        "    h.ship_to_state,  \r\n" + //
                        "    h.ship_to_zip,  \r\n" + //
                        "    h.ship_to_country,  \r\n" + //
                        "    h.ship_to_addr1,  \r\n" + //
                        "    h.ship_to_addr2,  \r\n" + //
                        "    h.ship_to_addr3,  \r\n" + //
                        "    h.ship_to_addr4,  \r\n" + //
                        "    h.ship_from_addr1,  \r\n" + //
                        "    h.ship_from_addr2,  \r\n" + //
                        "    h.ship_from_addr3,  \r\n" + //
                        "    h.ship_from_addr4,  \r\n" + //
                        "    h.ship_from_addr5,\r\n" + //
                        "    d.package_total_weight,\r\n" + //
                        "    d.package_total_volume,\r\n" + //
                        "    d.package_total_length,\r\n" + //
                        "    d.package_width,\r\n" + //
                        "    d.package_code,\r\n" + //
                        "    d.connote_number\r\n" + //
                        "FROM [dbo].[t_cus_itf_ob_ship_labels_detail] d\r\n" + //
                        "INNER JOIN [dbo].[t_cus_itf_ob_ship_labels_header] h ON h.uuid = d.uuid\r\n" + //
                        "WHERE d.itf_status = 'N';";


        String carrier_code = "StarShipIt"; // TODO: Verify this value from the database or input

        switch (carrier_code) {
            case "StarShipIt":
                //NewOrder("StarShipIt",dbData);
                
                break;
        
            default:
                break;
        } 
    
    //Get Data from the database
    List<Map<String,String>> dbData = DatabaseConnection.getData(DatabaseConnection.getConnection());
    NewOrderStarShipIt newOrderStarShipIt = new NewOrderStarShipIt(dbData);
    BulkLabelsStarShipIt bulkLabelsStarShipIt = new BulkLabelsStarShipIt(dbData);
    //bulkLabelsStarShipIt.addOrder_ids("575678813");

    //Store in memory or light db

    ObjectMapper objectMapper = new ObjectMapper();
    String orderBytes;
    String labelBytes;

    //TODO: Split these out into seperate functions
    try {
         orderBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newOrderStarShipIt);
         labelBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bulkLabelsStarShipIt);
         List<Integer> orderIds = postOrder(orderBytes, ExternalHeaders.starShipit());
         postLabel(labelBytes, ExternalHeaders.starShipit());


    } catch (Exception e) {
        System.err.println("Error converting StarShipIt object to JSON: " + e.getMessage());
        e.printStackTrace();
    }
   

    }

    public static List<Integer> postOrder(String jsonBytes, Headers headers) {
        System.out.println("PostOrder method called with JSON: " + jsonBytes);
        String url = null;
        String apiKey = null;
        String subscriptionKey = null;

        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("config.properties is not found");
            }
            props.load(input);
            url = props.getProperty("starshipit.api.url");
            apiKey = props.getProperty("starshipit.api.key");
            subscriptionKey = props.getProperty("starshipit.api.secret");
        } catch (IOException e) {
            System.err.println("Could not load database configuration from config.properties");
            e.printStackTrace();
        }

        try {
         OkHttpClient client = new OkHttpClient();
         MediaType mediaType = MediaType.parse("application/json");
         RequestBody body = RequestBody.create(mediaType, jsonBytes);
         Request request = new Request.Builder().url(url + "/orders/import")
                                                .method("POST", body)
                                                .headers(headers)
                                                .build();
         Response response = client.newCall(request).execute();
         System.out.println("Headers: "+ response.code()); // Check if the response is successful
         JsonNode jsonResponse = new ObjectMapper().readTree(response.body().string());// Print the labels to the console
         List<Integer> orderIds = new ArrayList<>();
            if (jsonResponse.has("orders")) {
                jsonResponse.get("orders").forEach(order -> {
                    if (order.has("order_id")) {
                        orderIds.add(order.get("order_id").asInt());
                    } else {
                        System.err.println("Order ID not found in response: " + order.toPrettyString());
                    }
                });
            } else {
                System.err.println("No 'orders' field found in the response.");
            }
         jsonResponse.get("orders").forEach(order -> {
             
         });
        System.out.println("This is the response orders:" + jsonResponse.toPrettyString()); // Read the response body to ensure the request is processed
        response.body().close(); // Close the response body to avoid resource leaks
        return orderIds;

    } catch (Exception e) {
        System.err.println(e.getMessage());
        System.err.println(e.getStackTrace());
    }
    return new ArrayList<>(); // Return an empty list if an error occurs
}

    
    public static void postLabel(String jsonBytes, Headers headers) {

        String url = null;
        String apiKey = null;
        String subscriptionKey = null;
        System.out.println("Working here");
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("config.properties is not found");
            }
            props.load(input);
            url = props.getProperty("starshipit.api.url");
            apiKey = props.getProperty("starshipit.api.key");
            subscriptionKey = props.getProperty("starshipit.api.secret");
        } catch (IOException e) {
            System.err.println("Could not load database configuration from config.properties");
            e.printStackTrace();
        }

        try {
            System.out.println("PostLabel method called with JSON: " + jsonBytes);  
         OkHttpClient client = new OkHttpClient();
         MediaType mediaType = MediaType.parse("application/json");
         RequestBody body = RequestBody.create(mediaType, jsonBytes);
         Request request = new Request.Builder().url(url + "/orders/shipments")
                                                .method("POST", body)
                                                .headers(headers)
                                                .build();
         Response response = client.newCall(request).execute();
        String rawResponse = response.body().string();
        System.out.println("Status: " + response.code());
        System.out.println("Response body: " + rawResponse);

        if (response.isSuccessful()) {
            JsonNode json = new ObjectMapper().readTree(rawResponse);
            System.out.println(json.toPrettyString());
        }
         JsonNode jsonResponse = new ObjectMapper().readTree(response.body().string());
         System.out.println("Headers: "+ response.code());
         
         System.out.println("This is the response labels:" + jsonResponse.toPrettyString()); // Read the response body to ensure the request is processedQ
         response.body().close(); // Close the response body to avoid resource leaks

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        
       
        
        
    }
    
    

}

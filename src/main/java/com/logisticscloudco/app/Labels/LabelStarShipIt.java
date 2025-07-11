package com.logisticscloudco.app.Labels;

import java.util.ArrayList;
import java.util.List;

public class LabelStarShipIt {
    /* 
    "order_id": "553251537",
    "order_number": "Test0005",
    "carrier": "Plain Label",
    "carrier_service_code": "Plain Label",
    "packages": [
        {
          "weight": 1,
          "height": 0.1,
          "width": 0.1,
          "length": 0.1,
          "packaging_type": ""
        }
      ],
    "reprint": "false" 
    */

    private String order_id;
    private String order_number;
    private String carrier;
    private String carrier_service_code;
    private ArrayList<String> packages;
    private String reprint;

    public LabelStarShipIt(String order_id,
                     String order_number,
                     String carrier, 
                     String carrier_service_code, 
                     String weight, 
                     String height, 
                     String width,
                     String length,
                     String packaging_type) {
        this.order_id = order_id;
        this.order_number = order_number;
        this.carrier = carrier;
        this.carrier_service_code = carrier_service_code;
        this.packages = new ArrayList<>();
        this.packages.add("weight: " + weight);
        this.packages.add("height: " + height);
        this.packages.add("width: " + width); // Default value
        this.packages.add("length: " + length); // Default value
        this.packages.add("packaging_type: " + packaging_type); // Default value
        this.reprint = "true"; // Default value
    }

    public void setOrder_Id(String order_id) {
        this.order_id = order_id;
    }
    public void setOrder_Number(String order_number) {
        this.order_number = order_number;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public void setCarrierServiceCode(String carrier_service_code) {
        this.carrier_service_code = carrier_service_code;
    }

    public String getOrder_Id() {
        return order_id;
    }

    public String getOrder_Number() {
        return order_number;
    }
    public String getCarrier() {
        return carrier;
    }
    public String getCarrierServiceCode() {
        return carrier_service_code;
    }
    public List<String> getPackages() {
        return packages;
    }
    public String getReprint() {
        return reprint;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("order_id\": \"").append(order_id).append("\",\n");
        sb.append("\"order_number\": \"").append(order_number).append("\",\n");
        sb.append("\"carrier\": \"").append(carrier).append("\",\n");
        sb.append("\"carrier_service_code\": \"").append(carrier_service_code).append("\",\n");
        sb.append("\"packages\": [\n");
        for (String pkg : packages) {
            sb.append("{ ").append(pkg).append(" },\n");
        }
        sb.append("],\n");
        sb.append("\"reprint\": \"").append(reprint).append("\"\n");
        sb.append("}");
        return sb.toString();
    }


}

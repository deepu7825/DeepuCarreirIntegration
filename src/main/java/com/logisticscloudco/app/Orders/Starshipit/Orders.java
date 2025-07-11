package com.logisticscloudco.app.Orders.Starshipit;
import java.util.List;

import com.logisticscloudco.app.Orders.Starshipit.Destination;
import com.logisticscloudco.app.Orders.Starshipit.SenderDetails;


public class Orders {
    

    /*  
     * "order_date": "2020-04-29T22:47:00.699943Z",
     * "order_number": "10001",
     * "shipping_method": "Express Shipping",
     * "signature_required": false,
     * "destination": { }
     *
     */

    private String order_date;
    private String order_number;
    private String reference;
    private String signature_required;
    private SenderDetails sender_details;
    private Destination destination;
    private List<Packages> packages;


    public Orders(String order_date, String order_number, String reference, String shipping_method, String signature_required, SenderDetails sender_details, Destination destination, List<Packages> packages) {
        this.order_date = order_date;
        this.order_number = order_number;
        this.reference = reference;
        this.signature_required = signature_required;
        this.sender_details = sender_details;
        this.destination = destination;
        this.packages = packages;
    }

    // Getters and Setters
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getOrder_number() {
        return order_number;
    }
    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getSignature_required() {
        return signature_required;
    }
    public void setSignature_required(String signature_required) {
        this.signature_required = signature_required;
    }
    public SenderDetails getSender_details() {
        return sender_details;
    }
    public void setSender_details(SenderDetails sender_details) {
        this.sender_details = sender_details;
    }
    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Packages> getPackages() {
        return packages;
    }
    public void setPackages(List<Packages> packages) {
        this.packages = packages;
    }



    @Override
    public String toString() {
        return "Orders{" +
                "order_date='" + order_date + '\'' +
                ", order_number='" + order_number + '\'' +
                ", reference='" + reference + '\'' +
                ", signature_required='" + signature_required + '\'' +
                ", sender_details=" + sender_details +
                ", destination=" + destination +
                ", packages=" + packages +
                '}';
    }
}

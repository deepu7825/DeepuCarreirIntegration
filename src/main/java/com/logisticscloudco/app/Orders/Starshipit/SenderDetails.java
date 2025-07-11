package com.logisticscloudco.app.Orders.Starshipit;

public class SenderDetails extends Addressable {

    public SenderDetails(String name, String phone, String street, String suburb, String state, String post_code,
            String country, String delivery_instructions) {
        super(name, phone, street, suburb, state, post_code, country, delivery_instructions);
    }

    // Getters and Setters
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }
    public String getPhone() {
        return super.getPhone();
    } 
    public void setPhone(String phone) {
        super.setPhone(phone);
    }  
    public String getStreet() {
        return super.getStreet();
    }
    public void setStreet(String street) {
        super.setStreet(street);
    }
    public String getSuburb() {
        return super.getSuburb();
    }
    public void setSuburb(String suburb) {
        super.setSuburb(suburb);
    }
    public String getState() {
        return super.getState();
    }   
    public void setState(String state) {
        super.setState(state);
    }
    public String getPostCode() {
        return super.getPostCode();
    }
    public void setPostCode(String post_code) {
        super.setPostCode(post_code);
    }
    public String getCountry() {
        return super.getCountry();
    }
    public void setCountry(String country) {
        super.setCountry(country);
    }
    public String getDeliveryInstructions() {
        return super.getDeliveryInstructions();
    }
    public void setDeliveryInstructions(String delivery_instructions) {
        super.setDeliveryInstructions(delivery_instructions);
    }
    @Override
    public String toString() {
        return "SenderDetails{" +
                "name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", street='" + getStreet() + '\'' +
                ", suburb='" + getSuburb() + '\'' +
                ", state='" + getState() + '\'' +
                ", post_code='" + getPostCode() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", delivery_instructions='" + getDeliveryInstructions() + '\'' +
                '}';
    }


    

}

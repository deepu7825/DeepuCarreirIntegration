package com.logisticscloudco.app.Orders.Starshipit;

public class Destination {
    /*
     *"destination": {
        "name": "Johnny Smith",
        "phone": "0290988227",
        "street": "20 George Street",
        "suburb": "Sydney",
        "state": "NSW",
        "post_code": "2000",
        "country": "Australia",
        "delivery_instructions": "Leave at door"
      },
     */
    private String name;
    private String phone;
    private String street;
    private String suburb;
    private String state;
    private String post_code;
    private String country;
    private String delivery_instructions;

    public Destination(String name, String phone, String street, String suburb, String state, String post_code, String country, String delivery_instructions) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.post_code = post_code;
        this.country = country;
        this.delivery_instructions = delivery_instructions;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getSuburb() {
        return suburb;
    }   
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPostCode() {
        return post_code;
    }
    public void setPostCode(String post_code) {
        this.post_code = post_code;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDeliveryInstructions() {
        return delivery_instructions;
    }
    public void setDeliveryInstructions(String delivery_instructions) {
        this.delivery_instructions = delivery_instructions;
    }   
    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", suburb='" + suburb + '\'' +
                ", state='" + state + '\'' +
                ", post_code='" + post_code + '\'' +
                ", country='" + country + '\'' +
                ", delivery_instructions='" + delivery_instructions + '\'' +
                '}';
    }

}

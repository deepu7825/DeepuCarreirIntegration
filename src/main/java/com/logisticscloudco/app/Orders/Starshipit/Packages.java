package com.logisticscloudco.app.Orders.Starshipit;

public class Packages {
    String package_id;	//integer	The unique numeric identifier for the package
    String name;	//Name of the package
    String weight;	//double	Physical weight of the parcel in kilograms (kg)
    String height;	//double	Height of the parcel in meters (m)
    String width;	//double	Width of the parcel in meters (m)
    String length;	//double	Length of the parcel in meters (m)
    String packaging_type; //string	The packaging type for this parcel. Example valid values: Carton, Box, Envelope, Item, Jiffy, Pallet, Satchel, Skid or Bag
    String tracking_number;	//string	Carrier tracking number
    String delete;	//boolean

    public Packages(String package_id, String name, String weight, String height, String width, String length, String packaging_type, String tracking_number, String delete) {
        this.package_id = package_id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.packaging_type = packaging_type;
        this.tracking_number = tracking_number;
        this.delete = "false";
    }
    // Getters and Setters
    public String getPackage_id() {
        return package_id;
    } 
    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }
    public String getName() {
        return name;
    }   
    public void setName(String name) {
        this.name = name;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPackaging_type() {
        return packaging_type;
    }
    public void setPackaging_type(String packaging_type) {
        this.packaging_type = packaging_type;
    }
    public String getTracking_number() {
        return tracking_number;
    }
    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
    public String getDelete() {
        return delete;
    }
    public void setDelete(String delete) {
        this.delete = delete;
    }
    @Override
    public String toString() {

        return "Package{" +
                "package_id='" + package_id + '\'' +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", packaging_type='" + packaging_type + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", delete='" + delete + '\'' +
                '}';
    }
    
    

}



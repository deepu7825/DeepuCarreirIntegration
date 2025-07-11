package com.logisticscloudco.app.Labels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BulkLabelsStarShipIt {


    private List<String> order_ids;
    private Boolean reprint;

    public BulkLabelsStarShipIt(List<Map<String, String>> dbData) {
        this.order_ids = populateOrderId(dbData);
        this.reprint = false;
    }

    public ArrayList<String> populateOrderId(List<Map<String, String>> dbData) {
        ArrayList<String> orderIds = new ArrayList<>();
        for (Map<String, String> data : dbData) {
            if (data.containsKey("order_id")) {
                try {
                    orderIds.add(data.get("order_id"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid order_id format: " + data.get("order_id"));
                }
            }
        }
        return orderIds;
    }

    public void setOrder_ids(List<String> order_ids) {
        this.order_ids = order_ids;
    }
    public void setReprint(Boolean reprint) {
        this.reprint = reprint;
    }
    public List<String> getOrder_ids() {
        return order_ids;
    }
    public Boolean getReprint() {
        return reprint;
    }

    public void addOrder_ids(String order_ids) {
        this.order_ids.add(order_ids);
    }
    public void addOrder_ids(List<String> order_ids) {
        this.order_ids.addAll(order_ids);
    }
    @Override
    public String toString() {
        return "LabelsStarShipIt{" +
                "order_id=" + order_ids +
                ", reprint='" + reprint + '\'' +
                '}';
    }

}

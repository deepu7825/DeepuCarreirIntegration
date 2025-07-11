package com.logisticscloudco.app.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.logisticscloudco.app.Orders.Starshipit.Destination;
import com.logisticscloudco.app.Orders.Starshipit.Orders;
import com.logisticscloudco.app.Orders.Starshipit.Packages;
import com.logisticscloudco.app.Orders.Starshipit.SenderDetails;

public class NewOrderStarShipIt {

    private List<Orders> orders;

    public NewOrderStarShipIt(List<Map<String, String>> dbData) {
        this.orders = new ArrayList<>();
        if (dbData == null || dbData.isEmpty()) {
            System.out.println("No data found in the database.");
            return; // Exit if no data is available
        }
        for (Map<String, String> row : dbData) {
          /*Packages packageDetails = new Packages(
                "Place123",
                "Package",
                row.get("package_total_weight"),
                row.get("package_total_height"),
                row.get("package_total_width"),
                row.get("package_total_length"),
                row.get("package_code"),
                row.get("connote_number"),
                "false"
            );
            */
            Packages packageDetails = new Packages(
                "Place123",
                "Package",
                "1.0",
                "1.1",
                "1.2",
                "3.0",
                "ABC123",
                "123-456",
                "false"
            );

            Orders order = new Orders(
                row.get("start_date"),
                row.get("order_id"),
                row.get("connote_number"),
                row.get("connote_number"),
                "false",
                new SenderDetails(
                    row.get("ship_from_name"),
                    row.get("ship_from_phone"),
                    concatAddress(row,false),
                    row.get("ship_from_supurb"), //TODO: Fix typo in "suburb" from db
                    row.get("ship_from_state"),
                    row.get("ship_from_zip"),
                    row.get("ship_from_country"),
                    row.get("shipping_instructions")
                ),
                new Destination(
                    row.get("ship_to_name"),
                    row.get("ship_to_phone"),
                    concatAddress(row,true),
                    row.get("ship_to_suburb"),
                    row.get("ship_to_state"),
                    row.get("ship_to_zip"),
                    row.get("ship_to_country"),
                    row.get("shipping_instructions")
                ),
                new ArrayList<Packages>(List.of(packageDetails)
                )
            );

            this.orders.add(order);
        }
    }

    /*
       * Concatenates the address fields from the database row into a single string.
       * @param dbRow A HashMap representing a row from the database.
       * @return A string containing the concatenated address.
       */
      public String concatAddress(Map<String, String> dbRow, boolean isToAddress) {
        String result = "";
        System.out.println(dbRow.get("ship_to_addr1"));

        if(isToAddress){
          for(int i = 1; i <= 4; i++) {
            if(dbRow.get("ship_from_addr" + i) == null || dbRow.get("ship_from_addr" + i).isEmpty()) {
              continue; // Skip empty address fields
            }
            if(i == 1){
              result = dbRow.get("ship_to_addr" + i);
            } else if (dbRow.get("ship_to_addr" + i) != null || !dbRow.get("ship_to_addr" + i).isEmpty()) {
              result += ", " + dbRow.get("ship_to_addr" + i);
            } 
          }
      } else {
          for(int i = 1; i <= 4; i++) {
            if(dbRow.get("ship_from_addr" + i) == null || dbRow.get("ship_from_addr" + i).isEmpty()) {
              continue; // Skip empty address fields
            }
            if(i == 1){
              result = dbRow.get("ship_from_addr" + i);
            } else if (dbRow.get("ship_from_addr" + i) != null || !dbRow.get("ship_from_addr" + i).isEmpty()) {
              result += ", " + dbRow.get("ship_from_addr" + i);
            } 
          }
      }
        return result;          
      }

    /**
     * Returns the list of orders.
     * @return A list of Orders objects.
     */
    public List<Orders> getOrders() {
        return orders;
    }
    /**
     * Sets the list of orders.
     * @param orders A list of Orders objects to set.
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /* 
    public Packages addDetails(List<Map<String, String>> dbData) {
        // This method can be used to add more details to the orders if needed.
        // Currently, it does nothing as the details are already populated in the constructor.
        System.out.println("Adding details from query: " + query);
                     return new Packages(
                    "Place123",
                    "Package",
                    dbData.get(0).get("package_total_weight"),
                    dbData.get(0).get("package_total_height"),
                    dbData.get(0).get("package_total_width"),
                    dbData.get(0).get("package_total_length"),
                    dbData.get(0).get("package_code"),
                    dbData.get(0).get("connote_number"),
                    "false"
                );
    }
                */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NewOrderStarShipIt{");
        sb.append("orders=").append(orders);
        sb.append('}');
        return sb.toString();
    }





}

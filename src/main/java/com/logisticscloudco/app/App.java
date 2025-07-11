package com.logisticscloudco.app;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logisticscloudco.app.Database.DatabaseConnection;
import com.logisticscloudco.app.Orders.NewOrder;
import com.logisticscloudco.app.Orders.NewOrderStarShipIt;
import com.logisticscloudco.app.PostLabel.PostLabel;


public class App {
    public static void main(String[] args) {
        //Get Label information
        //Post Label information to External Starshipit API
        //Get Base64 encoded PDF from Starshipit API

















        String sqlHeader = "SELECT * FROM [dbo].[t_cus_itf_ob_ship_labels_header]";
        
        ResultSet rs = DatabaseConnection.getQuery(DatabaseConnection.getConnection(), sqlHeader);
        List<Map<String, String>> dbData = new ArrayList<Map<String, String>>();
        
        try {
            if (rs != null && rs.next()) {  
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                HashMap<String, String> rowData = new HashMap<String, String>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);  
                    rowData.put(columnName, value);
                }
                System.out.println("Row data: " + rowData);
                dbData.add(rowData);
            } else {
                System.out.println("No rows returned from query.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        NewOrderStarShipIt newOrderStarShipIt = new NewOrderStarShipIt(dbData);
        PostLabel postLabel = new PostLabel(dbData.get(0), null); //TODO: Source correct index.

        System.out.println(newOrderStarShipIt.toString());
        System.out.println("New Order Starshipit: " + newOrderStarShipIt.toString());

        System.out.println("Started Starshipit API process.");
        CreatePDF.run("ss"); //Save PDF to server file system, with date on folder
        //NewOrder();
        //PostLabel();

    }
}

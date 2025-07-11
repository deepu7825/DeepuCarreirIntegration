package com.logisticscloudco.app.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnection {
    private static String URL;
    private static String NAME;
    private static String USERNAME;
    private static String PASSWORD;
    private static String ENCRYPT;
    private static String TRUST;
    private static String DBSTRING;

    static {
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("config.properties is not found");
            }
            props.load(input);
            URL = props.getProperty("db.url");
            NAME = props.getProperty("db.name");
            USERNAME = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");
            ENCRYPT = props.getProperty("db.encrypt");
            TRUST = props.getProperty("db.trust");
            DBSTRING = URL + NAME + USERNAME + PASSWORD + ENCRYPT + TRUST;
            
        } catch (IOException e) {
            System.err.println("Could not load database configuration from config.properties or JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public static ResultSet getQuery(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Query executed successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to fetch statement from query");
            e.printStackTrace();
        }
        return resultSet;
        
        
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBSTRING);
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    private static String fetchNewOrderQuery(List<String> orderIDs) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT\r\n" + //
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
                        "FROM [dbo].[t_cus_itf_ob_ship_labels_header] h\r\n" + //
                        "LEFT JOIN [dbo].[t_cus_itf_ob_ship_labels_detail] d\r\n" + //
                        "    ON h.uuid = d.uuid\r\n" + //
                        "WHERE h.order_id IN (");
        
        for (int i = 0; i < orderIDs.size(); i++) {
            queryBuilder.append("'").append(orderIDs.get(i)).append("'");
            if (i < orderIDs.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        
        queryBuilder.append(");");
        System.out.println("Generated query: " + queryBuilder.toString());
        return queryBuilder.toString();
    }

    public static List<Map<String, String>> getData(Connection connection){
        List<String> orderIDs = updateStatus(connection);
        if(orderIDs.isEmpty()) {
            System.out.println("No order IDs to process.");
            return new ArrayList<Map<String, String>>(); // Return empty list if no order IDs
        }
        ResultSet rs = DatabaseConnection.getQuery(connection, fetchNewOrderQuery(orderIDs));
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

        return dbData;

    }

private static List<String> updateStatus(Connection connection) {
    List<String> orderIds = new ArrayList<>();

    String query =
        "UPDATE h\n" +
        "SET itf_status = 'P'\n" +
        "OUTPUT inserted.order_id\n" +
        "FROM [dbo].[t_cus_itf_ob_ship_labels_header] h\n" +
        "WHERE h.itf_status = 'N';";

    if (connection != null) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                orderIds.add(resultSet.getString("order_id"));
            }
            System.out.println("Status updated successfully. Order IDs: " + orderIds);

        } catch (SQLException e) {
            System.err.println("Failed to update status in the database.");
            e.printStackTrace();
        }
    }

    return orderIds;
}


    public static void postData(String Query) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(Query);
                System.out.println("Data posted successfully. Rows affected: " + rowsAffected);
            } catch (SQLException e) {
                System.err.println("Failed to post data to the database.");
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test the connection
        Connection connection = getConnection();
        if (connection != null) {
            try {

                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

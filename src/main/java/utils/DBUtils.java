package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtils {
static String url = "jdbc:mysql://localhost:3306/testdb";
static String username = "root";
static String password = "Imironman@007";

public static void getCustomerData() {
	try {
		// Create a conection to the database
		Connection conn = DriverManager.getConnection(url,username,password);
		
		//Create a statement object to execute SQL queries
		Statement stmt = conn.createStatement();
		
		//Execute a SQL query to retrieve customer data
		ResultSet rs  = stmt.executeQuery("SELECT * FROM customers");
		
		// Print results
        while(rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getInt("age"));
        }
        
        conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}

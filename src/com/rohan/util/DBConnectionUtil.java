package com.rohan.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {

	// define the db properties

	private static final String username = "root";

	private static final String password = "Ramesh@1994";

	private static final String jdbcURL = "jdbc:mysql://localhost:3306/employeedirectory";

	private static final String driver = "com.mysql.jdbc.Driver";

	// refernece variable to hold connection object
	private static Connection connection = null;

	// define the static methods

	public static Connection openConnection() {

		// check the connection
		if (connection != null) {
			return connection;
		} else {

			try {
				// load the driver
				Class.forName(driver);

				// get the connection
				connection = DriverManager.getConnection(jdbcURL, username, password);

			} catch (Exception e) {
				e.printStackTrace();
			}

			// return the connection
			return connection;
		}

	}

}

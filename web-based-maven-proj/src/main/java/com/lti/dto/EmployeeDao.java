package com.lti.dto;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;

//Data Access Object
public class EmployeeDao {

	// public void insert(int empno, String name, double salary)
	public boolean insert(Employee emp) throws DataAccessException {
		PreparedStatement pstmt = null;
		Connection conn = null;

		// Step 1. Loading the JDBC Driver
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("prod-db.properties");

			Properties dbProps = new Properties();
			dbProps.load(is);

			String driverClassName = dbProps.getProperty("driverClassName");

			Class.forName(driverClassName);

			// Step 2. Now we can try connecting to the database
			String url = dbProps.getProperty("url");
			String user = dbProps.getProperty("username");
			String pass = dbProps.getProperty("password");

			//Get connection from database
			conn = DriverManager.getConnection(url, user, pass);

			// Insert into database
			pstmt = conn.prepareStatement("insert into employee1 values(?,?,?)");
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setDouble(3, emp.getEsal());
			pstmt.executeUpdate();

			return true;

		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new DataAccessException("Exception in database",e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}

package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {

		public static Connection getConnection() 
		{
			Connection connection = null;
			Properties properties = new Properties();
			
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\Jahanvi\\Desktop\\Training\\TruYum\\truYumJDBC\\src\\connection.properties");
				properties.load(fis);
				String url = properties.getProperty("url");
				String username = properties.getProperty("user");
				String password = properties.getProperty("password");
				connection = DriverManager.getConnection(url, username, password);
				
			}catch(FileNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return connection;
		}
}

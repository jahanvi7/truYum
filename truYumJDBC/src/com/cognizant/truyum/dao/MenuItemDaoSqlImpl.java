package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		List<MenuItem> list = new ArrayList<MenuItem>();
		Connection connection = ConnectionHandler.getConnection();
		
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement("select * from menu_item");
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next())
			{
				long menuId = rs.getLong(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				String active = rs.getString(4);
				java.sql.Date dateOfLaunch = rs.getDate(5);
				String category = rs.getString(6);
				String freeD = rs.getString(7);
				boolean act = false;
				if(active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean freeDelivery = false;
				if(freeD.equalsIgnoreCase("yes")) {
					freeDelivery = true;
				}
				MenuItem menu = new MenuItem(menuId, name, price, act, dateOfLaunch, category, freeDelivery);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> list = new ArrayList<MenuItem>();
		Date date1 = new Date();
		java.sql.Date date2 = new java.sql.Date(date1.getTime());
		String sql = "select * from menu_item where active = 'Yes' and date_of_launch < ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, date2);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				boolean active = (rs.getString(4).equals("Yes")) ? true : false;
				Date dat = rs.getDate(5);
				String category = rs.getString(6);
				boolean freeDelivery = (rs.getString(7).contentEquals("Yes") ? true : false);
				MenuItem menu = new MenuItem(id, name, price, active, dat, category, freeDelivery);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection connection = ConnectionHandler.getConnection();
		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		Date dat = menuItem.getDateOfLaunch();
		String category = menuItem.getCategory();
		boolean freeDelivery = menuItem.isFreeDelivery();
		String act = "No";
		if(active == true)
		{
			act = "Yes";
		}
		String free = "No";
		if(freeDelivery == true)
		{
			free = "Yes";
		}
		String sql = "update menu_item set name = ?, price = ?, active = ?, date_of_launch = ?, category = ?, free_delivery =? where menu_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setFloat(2, price);
			preparedStatement.setString(3, act);
			preparedStatement.setDate(4,new java.sql.Date(dat.getTime()));
			preparedStatement.setString(5, category);
			preparedStatement.setString(6, free);
			preparedStatement.setLong(7, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId){
		Connection connection = ConnectionHandler.getConnection();
		String sql = "select * from menu_item where menu_id = ?";
		PreparedStatement preparedStatement;
		MenuItem menuItem = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, menuItemId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				long id = rs.getLong(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				boolean active = (rs.getString(4).contentEquals("Yes") ? true : false);
				Date dat = rs.getDate(5);
				String category = rs.getString(6);
				boolean freeDelivery = (rs.getString(7).contentEquals("Yes") ? true : false);
				menuItem = new MenuItem(id, name, price, active, dat, category, freeDelivery);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return menuItem;
	}
	

}

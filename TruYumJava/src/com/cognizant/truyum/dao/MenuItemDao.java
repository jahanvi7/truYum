package com.cognizant.truyum.dao;

import java.sql.SQLException;
import java.util.*;

import com.cognizant.truyum.model.MenuItem;

public interface MenuItemDao {
	public List<MenuItem> getMenuItemListAdmin() throws SQLException;
	
	public List<MenuItem> getMenuItemListCustomer();
	
	public void modifyMenuItem(MenuItem menuItem);
	
	public MenuItem getMenuItem(long menuItemId);
}

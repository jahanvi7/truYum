package com.cognizant.truyum.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Service("menuItemService")
public class MenuItemService {

	@Autowired
	@Qualifier("menuItemDaoCollectionImpl")
	private MenuItemDao menuItemDao;
	
	public void setMenuItemDao(MenuItemDao menuItemDao) {
		
	}
	
	public List<MenuItem> getMenuItemListAdmin(){
		try {
			return menuItemDao.getMenuItemListAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MenuItem> getMenuItemListCustomer(){
		return menuItemDao.getMenuItemListCustomer();
	}
	
	public MenuItem getMenuItem(long menuItemId) {
		return menuItemDao.getMenuItem(menuItemId);
	}
	
	public void modifyMenuItem(MenuItem menuItem) {
			menuItemDao.modifyMenuItem(menuItem);
	}
}

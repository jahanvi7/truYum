package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private static List<MenuItem> menuItemList;
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> fil = new ArrayList<MenuItem>();
		for(MenuItem x : menuItemList)
		{
			Date d = x.getDateOfLaunch();
			Date today = new Date();
			if(d.before(today) || d.equals(today))
			{
				if(x.isActive())
					fil.add(x);
			}
		}
		return fil;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		for(MenuItem item : menuItemList) {
			if(item.equals(menuItem)) {
				item.setName(menuItem.getName());
				item.setPrice(menuItem.getPrice());
				item.setActive(menuItem.isActive());
				item.setDateOfLaunch(menuItem.getDateOfLaunch());
				item.setCategory(menuItem.getCategory());
				item.setFreeDelivery(menuItem.isFreeDelivery());
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		for(MenuItem x : menuItemList) {
			if(x.getId()== menuItemId) {
				return x;
			}
		}
		return null;
	}
	
	public MenuItemDaoCollectionImpl()
	{
		if(menuItemList == null || menuItemList.isEmpty()) 
		{
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(new MenuItem((long) 01, "Sandwich",  (float) 99.0, true, DateUtil.convertToDate("15/03/2017"), "Main Course",  true));
			menuItemList.add(new MenuItem((long) 02, "Burger", (float)129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			menuItemList.add(new MenuItem((long) 03, "Pizza", (float)149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(new MenuItem((long) 04, "French Fries", (float)57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			menuItemList.add(new MenuItem((long) 05, "Chocolate Brownie",(float)32.0, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true));
		}
	}

}

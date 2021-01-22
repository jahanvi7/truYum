package com.cognizant.truyum.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

		public static void main(String[] args) 
		{
			testGetMenuItemListAdmin();
			testGetMenuItemListCustomer();
			testModifyMenuItem();
			testGetMenuItem();
		}
		
		public static void testGetMenuItemListAdmin()
		{
			MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
			List<MenuItem> list = new ArrayList<>();
			list = menuItemDao.getMenuItemListAdmin();
			System.out.println("Menu List for Admin\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for(MenuItem menu : list)
			{
				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s",menu.getName(),menu.getPrice(),menu.isActive(),menu.getDateOfLaunch(),menu.getCategory(),menu.isFreeDelivery()));
			}
		}
		
		public static void testGetMenuItemListCustomer()
		{
			MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
			List<MenuItem> list = new ArrayList<>();
			list = menuItemDao.getMenuItemListCustomer();
			System.out.println("\nMenu List for Customer\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for(MenuItem menu : list)
			{
				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s",menu.getName(),menu.getPrice(),menu.isActive(),menu.getDateOfLaunch(),menu.getCategory(),menu.isFreeDelivery()));
			}
		}
		
		public static void testModifyMenuItem()
		{
			MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
			MenuItem menu = new MenuItem(05, "Chocolate Brownie", (float) 150.0, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true);
			menuItemDao.modifyMenuItem(menu);
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("\nModified Item\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s",menu.getName(),menu.getPrice(),menu.isActive(),simple.format(menu.getDateOfLaunch()),menu.getCategory(),menu.isFreeDelivery()));
		}
		
		public static void testGetMenuItem()
		{
			MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
			MenuItem menu = menuItemDao.getMenuItem(4);
			System.out.println("\nChoosen menu item\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s",menu.getName(),menu.getPrice(),menu.isActive(),menu.getDateOfLaunch(),menu.getCategory(),menu.isFreeDelivery()));
		}
}

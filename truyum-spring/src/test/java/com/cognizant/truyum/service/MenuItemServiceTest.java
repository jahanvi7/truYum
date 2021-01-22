package com.cognizant.truyum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemServiceTest {
	
	MenuItemService menuItemService;
	
	public void initializeService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		context.scan("com.cognizant.truyum");
		context.refresh();
		menuItemService = (MenuItemService) context.getBean("menuItemService");
	}
	
	@Test
	public void testGetMenuItemListAdminSize() {
		assertEquals(5, menuItemService.getMenuItemListAdmin().size());
	}
	
	@Test
	public void testGetMenuItemListAdminContainsSandwich() {
		MenuItem menuItem = new MenuItem(1, "Sandwich", (float)99.0, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true);
		for(MenuItem menuItemInList : menuItemService.getMenuItemListAdmin()) {
			if(menuItemInList.equals(menuItem)) {
				assertEquals(menuItem.getName(), menuItemInList.getName());
				assertEquals(menuItem.getPrice(), menuItemInList.getPrice());
				assertEquals(menuItem.isActive(), menuItemInList.isActive());
				assertEquals(menuItem.getDateOfLaunch(), menuItemInList.getDateOfLaunch());
				assertEquals(menuItem.getCategory(), menuItemInList.getCategory());
				assertEquals(menuItem.isFreeDelivery(), menuItemInList.isFreeDelivery());
			}
		}
	}
	
	@Test
	public void testGetMenuItemListCustomerSize() {
		assertEquals(3, menuItemService.getMenuItemListCustomer().size());
	}
	
	@Test
	public void testGetMenuItemListCustomerNotContainsFrenchFries() {
		MenuItem menuItem = new MenuItem(4, "French Fries", (float)57.0, false, 
				DateUtil.convertToDate("02/07/2017"), "Starters", true);
		for(MenuItem menuItemInList : menuItemService.getMenuItemListCustomer()) {
			assertFalse(menuItemInList.equals(menuItem));
		}
	}
	
	@Test
	public void testGetMenuItem() {
		assertEquals(1, menuItemService.getMenuItem(1).getId());
	}
	
	@Test
	public void testModifyMenuItem() {
		MenuItem menuItem = new MenuItem((long) 1, "Big Sandwich", (float) 199.0, true,
				DateUtil.convertToDate("15/03/2019"), "Main Course", true);
		menuItemService.modifyMenuItem(menuItem);
		assertEquals("testModifyMenuItem1", menuItem.getName(), menuItemService.getMenuItem(1).getName());
		assertEquals("testModifyMenuItem2", menuItem.getPrice(), menuItemService.getMenuItem(1).getPrice());
		assertEquals("testModifyMenuItem3", menuItem.getDateOfLaunch(),
				menuItemService.getMenuItem(1).getDateOfLaunch());
		MenuItem menuItem1 = new MenuItem((long) 1, "Sandwich", (float) 99.0, true,
				DateUtil.convertToDate("15/03/2017"), "Main Course", true);
		menuItemService.modifyMenuItem(menuItem1);
	}
}

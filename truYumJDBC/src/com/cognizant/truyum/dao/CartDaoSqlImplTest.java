package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void main(String[] args) 
	{
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();
	}
	
	public static void testAddCartItem()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1, 1);
		cartDao.addCartItem(1, 3);
		List<MenuItem> list;
		try {
			list = cartDao.getAllCartItems(1);
			System.out.println("Items Added to cart successfully\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for (MenuItem item : list) {

				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n",item.getName(),item.getPrice(),item.isActive(),item.getDateOfLaunch(),item.getCategory(),item.isFreeDelivery()));

			}
		} catch (CartEmptyException  e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testGetAllCartItems()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		try {
			List<MenuItem> list = cartDao.getAllCartItems(1);
			System.out.println("\nAll Cart Items\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for (MenuItem item : list) {

				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n",item.getName(),item.getPrice(),item.isActive(),item.getDateOfLaunch(),item.getCategory(),item.isFreeDelivery()));

			}
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
	}
	
	public static void testRemoveCartItem()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(1, 3);
		System.out.println("\nItem removed from cart\n");
		try {
			List<MenuItem> list = cartDao.getAllCartItems(1);
			System.out.println("\nAll Cart Items\n");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for (MenuItem item : list) {

				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n",item.getName(),item.getPrice(),item.isActive(),item.getDateOfLaunch(),item.getCategory(),item.isFreeDelivery()));

			}
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
	}

}

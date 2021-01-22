package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String[] args) {
		testAddCartItem();
		testRemoveCartItem();
	}

	public static void testAddCartItem() {
		CartDao cartDao = new CartDaoCollectionImpl();
		cartDao.addCartItem(1, 1);
		cartDao.addCartItem(1, 2);
		try {
			List<MenuItem> cartList = cartDao.getAllCartItems(1);
			System.out.println("item added to cart successfully\n");
			System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for(MenuItem x : cartList)
			{
				System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n",x.getName(),x.getPrice(),x.isActive(),x.getDateOfLaunch(),x.getCategory(),x.isFreeDelivery()));
			}
		} catch (CartEmptyException e) {
			System.out.println("Cart is Empty");
		}
	}

	public static void testGetAllCartItems() {
		CartDao cartDao = new CartDaoCollectionImpl();
		List<MenuItem> cartItem;
		try {
			cartItem = cartDao.getAllCartItems(1);
			System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for(MenuItem item : cartItem)
			{
				System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n",item.getName(),item.getPrice(),item.isActive(),item.getDateOfLaunch(),item.getCategory(),item.isFreeDelivery()));				
			}
		} catch (CartEmptyException e) {
			System.out.println("Cart is Empty");
		}
		
	}

	public static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoCollectionImpl();
		cartDao.removeCartItem(1, 1);
		try {
			List<MenuItem> cartItem = cartDao.getAllCartItems(1);
			System.out.println("After removing item from cart\n");
			System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n","Name","Price","Active","Date Of Launch","Category","Free Delivery"));
			for(MenuItem item : cartItem)
			{
				System.out.println(String.format("%-25s%-25s%-25s%-40s%-25s%-20s\n",item.getName(),item.getPrice(),item.isActive(),item.getDateOfLaunch(),item.getCategory(),item.isFreeDelivery()));				
			}
		} catch (CartEmptyException e) {
			System.out.println("Cart is Empty");
		}
		
	}
}

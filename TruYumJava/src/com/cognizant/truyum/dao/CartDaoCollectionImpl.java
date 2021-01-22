package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	
	public static HashMap<Long, Cart> userCarts;
	List<MenuItem> menuItemList;
	
	@Override
	public void addCartItem(long userId, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if(userCarts.containsKey(userId))
		{
			Cart cart = userCarts.get(userId);
			menuItemList = cart.getMenuItemList();
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			cart.setTotal(cart.getTotal()+menuItem.getPrice());
			userCarts.put(userId, cart);
		}
		else
		{
			ArrayList<MenuItem> cartMenu = new ArrayList<MenuItem>();
			cartMenu.add(menuItem);
			Cart newCart = new Cart(cartMenu,0);
			userCarts.put(userId, newCart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> all = userCarts.get(userId).getMenuItemList();
		if(all.isEmpty()) 
		{
			throw new CartEmptyException();
		}
		else
		{
			float sum = 0.00f;
			for(MenuItem item: all) 
			{
				sum += item.getPrice();
			}
			userCarts.get(userId).setTotal(sum);
		}
		return all;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		List<MenuItem> list = userCarts.get(userId).getMenuItemList();
		for(MenuItem item : list) {
			if(menuItemId == item.getId())
			{
				list.remove(list.indexOf(item));
				break;
			}
		}
		userCarts.get(userId).setMenuItemList(list);   
		}
	
	public CartDaoCollectionImpl() {
		if(userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

}

package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Component("cartDaoCollectionImpl")
public class CartDaoCollectionImpl implements CartDao {
	
	@Autowired
	@Qualifier("menuItems")
	private MenuItemDao menuItemDao;
	
	public HashMap<Long, Cart> userCarts;
	List<MenuItem> menuItemList;
	
	@Autowired
	public CartDaoCollectionImpl(LinkedHashMap<Long, Cart> userCarts) {
		super();
		this.userCarts = userCarts;
	}
	
	public void addCartItem(long userId, long menuItemId) {
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

}

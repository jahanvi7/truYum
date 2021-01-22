package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userId, long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		String sql = "insert into cart values (?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, menuItemId);
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
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> list = new ArrayList<>();
		Cart cart = new Cart(list, 0);
		double total = 0.0;
		String sql = "select * from menu_item inner join cart on menu_item.menu_id = cart.cart_menu_id where cart.cart_user_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				total += price;
				boolean active = (rs.getString(4).contentEquals("Yes") ? true : false);
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
		cart.setTotal(total);
		cart.setMenuItemList(list);
		return list;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		String sql = "delete from cart where cart_user_id = ? and cart_menu_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, menuItemId);
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

}

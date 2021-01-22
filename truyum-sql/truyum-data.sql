-- View Menu Item List Admin (TYUC001)

insert into menu_item values
(1, 'Sandwich', 99.00, 'Yes', '2017-03-15', 'Main Course', 'Yes'),
(2, 'Burger' , 129.00, 'Yes', '2017-12-23', 'Main Course', 'No'),
(3, 'Pizza', 149.00, 'Yes', '2017-08-21', 'Main Course', 'No'),
(4, 'French Fries', 57.00, 'No', '2017-07-02', 'Starters', 'Yes'),
(5, 'Chocolate Brownie', 32.00, 'Yes', '2022-11-02', 'Dessert', 'Yes');

select * from menu_item;

-- View Menu Item List Customer (TYUC002)

select * from menu_item
where date_of_launch <= curdate() and 
active = 'Yes';

-- Edit Menu Item (TYUC003)

select name from menu_item
where menu_id = 1;

update menu_item
set menu_id = 1, name = 'Sandwich', price = 99.00, active = 'Yes', 
date_of_launch = '2017-03-15', category = 'Main Course', free_delivery = 'Yes'
where menu_id = 1;

-- Add to Cart (TYUC004)

insert into user values
(1, 'user1'),
(2, 'user2');

insert into cart values
(1, 1, 1),
(2, 1, 2),
(3, 1, 3);

-- View Cart (TYUC005)

select m.name as menu_items from menu_item m
inner join cart c on m.menu_id = c.cart_menu_id
where cart_user_id = 1; 

select sum(m.price) as total_price from menu_item m
inner join cart c on m.menu_id = c.cart_menu_id
where cart_user_id = 1;

-- Remove Item from Cart (TYUC006)
delete from cart 
where cart_user_id = 1
and cart_menu_id = 3;

var foodData = [
    { id: 1, name: 'Sandwich', price: 99, active: 'Yes', dateOfLaunch: '15/03/2017', category: 'Main Course', freeDelivery: 'Yes' },
    { id: 2, name: 'Burger', price: 129, active: 'Yes', dateOfLaunch: '23/12/2017', category: 'Main Course', freeDelivery: 'No' },
    { id: 3, name: 'Pizza', price: 149, active: 'Yes', dateOfLaunch: '21/08/2017', category: 'Main Course', freeDelivery: 'No' },
    { id: 4, name: 'French Fries', price: 57, active: 'No', dateOfLaunch: '02/07/2017', category: 'Starter', freeDelivery: 'Yes' },
    { id: 5, name: 'Chocolate Brownies', price: 32, active: 'Yes', dateOfLaunch: '02/11/2022', category: 'Dessert', freeDelivery: 'Yes' }
]

var cart= [];

function addToCart(x){
    for(var y of foodData){
        if(x=== y.name){
            cart.push(y);
        }
    }
    console.log(cart);
}

function viewCart(){
    var t= document.getElementById('cart-table');
    console.log(cart);
    for (var item of cart) {
        console.log(item);
        let row = document.createElement('tr');
        let col1 = document.createElement('td');
        let col2 = document.createElement('td');
        let col3 = document.createElement('td');
        let deleteFromCart = document.createElement('a');
        deleteFromCart.textContent = 'Delete';

        col1.textContent = item.name;
        row.appendChild(col1);

        col2.textContent = item.freeDelivery;
        row.appendChild(col2);

        col3.textContent = item.price;
        row.appendChild(col3);

        t.appendChild(row);

        deleteFromCart.href = "#";
        deleteFromCart.onclick = function () {
            cart.pop(item);
        }
    }
}

function validate(){
    //var alpha = "[A-Za-z\\s]+";
    //var n = /^[0-9]+$/;
    var a = document.editForm.name.value;
    var i = new Date(document.editForm.dateOfLaunch.value);
    if(document.editForm.name.value == ""){
        alert("Title is required.");
        document.editForm.name.focus() ;
        return false;
    }
    if(!/^[a-zA-Z]*$/g.test(a) || (a.length<2) || (a.length>65)){
        alert("Title should have 2 to 65 characters.");
        document.editForm.name.focus() ;
        return false;
    }
    if(document.editForm.price.value == "" || document.editForm.price.value == null){
        alert("Price is required.");
        document.editForm.price.focus();
        return false;
    }
    if(!document.editForm.price.value.match(/^\d+/)){
        alert("Price has to be a number");
        document.editForm.price.focus() ;
        return false;
    }
    if(i.value == ""){
        alert("Date of Launch is required.");
        document.editForm.dateOfLaunch.focus() ;
        return false;
    }
   // window.location= "edit-menu-item-status.html";
    return true;
}
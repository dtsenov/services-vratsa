function addToCart(name, price) {
//
// // let BASE_URL = ""
// let addToCartBtn = document.getElementById("add-to-cart-btn");
// let name = document.getElementById("name");
// let price = document.getElementById("price");

let totalCartPrice = 0;


    const cartItemsElement = document.getElementById("cartItems");
    const totalPriceElement = document.getElementById("totalPrice");

    const cartItemElement = document.createElement("div");
    cartItemElement.className = 'cart-item';

    const itemNameElement = document.createElement('span');
    itemNameElement.textContent = name + price.toFixed(2) + ' лв.';

    const deleteBtnElement = document.createElement('button');
    deleteBtnElement.textContent = 'Изтрий';
    deleteBtnElement.className = 'delete-btn';
    deleteBtnElement.onclick = function () {
        cartItemsElement.removeChild(cartItemElement);
        totalCartPrice -= price;
        totalPriceElement.textContent = totalCartPrice.toFixed(2);
    }

    cartItemElement.appendChild(itemNameElement);
    cartItemElement.appendChild(deleteBtnElement);

    cartItemsElement.appendChild(cartItemElement);

    totalCartPrice += price;
    totalPriceElement.textContent = totalCartPrice.toFixed(2);

}

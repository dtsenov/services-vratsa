function addProductToCart() {
    let BASE_URL = "/products/all/";
    let productId = document.getElementById("productId").innerText;

    let csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch(BASE_URL + productId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            productId: productId
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Продуктът не се добави в количката.');
            }
            return response.text();
        })
        .then(data => {
            console.log('Успешно добавен продукт: ', data);
            reloadCart();
        })
        .catch(error => {
            console.error('ГРЕШКА: ', error.message);
        });


}

function addServiceToCart() {
    let BASE_URL = "/services/all/";
    let serviceId = document.getElementById("serviceId").innerText;

    let csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch(BASE_URL + serviceId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            serviceId: serviceId
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Услугата не се добави в количката.');
            }
            return response.text();
        })
        .then(data => {
            console.log('Успешно добавена услуга: ', data);
            reloadCart();
        })
        .catch(error => {
            console.error('ГРЕШКА: ', error.message);
        });

}

function reloadCart() {
    const cartItems = document.getElementById('cartItems');
    const cart = document.getElementById('cart');
    let BASE_URL = "/cart";
    const totalPrice = document.createElement('p');

    fetch(BASE_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Грешка при извличане на данни от количката. Статус код: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {

            if (data.length <= 0) {
                cartItems.innerHTML = '';
                let emptyCart = document.createElement('p');
                emptyCart.textContent = "Количката е празна";

                cartItems.appendChild(emptyCart);
            } else {

                let totalCartPrice = 0.00;
                cartItems.innerHTML = '';

                data.forEach(product => {
                    totalCartPrice += product.price;
                    const productNameElement = document.createElement('div');
                    const productQuantityElement = document.createElement('div');
                    const productPriceElement = document.createElement('div');
                    const deleteFromCartBtn = document.createElement('button');
                    const currentItemContainer = document.createElement('div');

                    currentItemContainer.className = 'currentCartItem';


                    productNameElement.textContent = `Име: ${product.name}`;
                    productQuantityElement.textContent = `Количество: ${product.quantity}бр.`
                    productPriceElement.textContent = `Цена: ${product.price.toFixed(2)}лв.`;

                    deleteFromCartBtn.textContent = 'Изтрий'
                    deleteFromCartBtn.setAttribute('productId', product.id);
                    deleteFromCartBtn.className = 'delete-cart-btn';
                    deleteFromCartBtn.addEventListener('click', deleteFromCart);

                    totalPrice.className = 'totalCartPrice';
                    totalPrice.textContent = `Обща цена: ${totalCartPrice.toFixed(2)}лв.`;

                    currentItemContainer.appendChild(productNameElement);
                    currentItemContainer.appendChild(productQuantityElement)
                    currentItemContainer.appendChild(productPriceElement);
                    currentItemContainer.appendChild(deleteFromCartBtn);

                    cartItems.appendChild(currentItemContainer);
                });

                const confirmCartBtn = document.createElement("button");
                confirmCartBtn.addEventListener('click', confirmCart);
                confirmCartBtn.textContent = 'ЗАВЪРШИ ПОРЪЧКА';
                confirmCartBtn.className = 'confirm-order-btn';


                cartItems.appendChild(totalPrice);
                cartItems.appendChild(confirmCartBtn);

            }
        })
        .catch(error => console.error('Грешка при зареждане на количката:', error.message));
}

function deleteFromCart() {

    const productId = event.currentTarget.getAttribute('productId');
    const BASE_URL = "/cart/";

    let csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch(BASE_URL + productId, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrfToken
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Грешка при изтриване на продукта от количката.');
            }
            console.log('Продуктът е успешно премахнат от количката.');
            reloadCart();
        })
        .catch(error => console.error('ГРЕШКА: ', error.message));
}

function confirmCart() {
    window.location.replace('/make-order');
}
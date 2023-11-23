function addToCart() {
    let BASE_URL = "/products/all/";
    let productId = document.getElementById("productId").innerText;
    let addToCartBtn = document.getElementById("addToCartBtn");

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

function reloadCart() {
    const cartItems = document.getElementById('cartItems');
    let BASE_URL = "/cart";

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

                cartItems.innerHTML = '';

                data.forEach(product => {
                    const productNameElement = document.createElement('div');
                    const productQuantityElement = document.createElement('div');
                    const productPriceElement = document.createElement('div');
                    const deleteFromCartBtn = document.createElement('button');

                    productNameElement.textContent = `Име: ${product.name}`;
                    productQuantityElement.textContent = `Количество: ${product.quantity}бр.`
                    productPriceElement.textContent = `Цена: ${product.price.toFixed(2)}лв.`;

                    deleteFromCartBtn.textContent = 'Изтрий'
                    deleteFromCartBtn.setAttribute('productId', product.id);
                    deleteFromCartBtn.addEventListener('click', deleteFromCart);

                    cartItems.appendChild(productNameElement);
                    cartItems.appendChild(productQuantityElement)
                    cartItems.appendChild(productPriceElement);
                    cartItems.appendChild(deleteFromCartBtn);
                });
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

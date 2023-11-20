async function addToCart() {
    let BASE_URL = "/products/all";
    let productId = document.getElementById("productId").value;

    try {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                productId: productId
            })
        });

        if (!response.ok) {
            throw new Error('Продуктът не се добави в количката.');
        }

        const data = await response.json();
        console.log('Успешно добавен продукт: ', data);
    } catch (error) {
        console.error('ГРЕШКА: ', error.message);
    }
}

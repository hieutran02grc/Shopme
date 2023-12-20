// <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  $(document).ready(function () {
        // Replace 'YOUR_API_ENDPOINT' with the actual API endpoint URL
        var apiEndpoint = 'YOUR_API_ENDPOINT';

        $.ajax({
            url: apiEndpoint,
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                // Assuming the API response contains an array of products
                displayProductList(data);
            },
            error: function (error) {
                console.error('Error fetching data:', error);
            }
        });

        function displayProductList(products) {
            var productListContainer = $('#product-list');
            var productListHtml = '<h2>Product List</h2><ul>';

            products.forEach(function (product) {
                productListHtml += '<li>' + product.name + ' - $' + product.price + '</li>';
            });

            productListHtml += '</ul>';
            productListContainer.html(productListHtml);
        }
    });
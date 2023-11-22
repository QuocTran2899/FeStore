$(document).ready(function () {
    // Lấy danh sách sản phẩm
    $.ajax({
        url: "http://localhost:8080/api/product",
        method: "GET",
    }).done(function (data) {
        console.log("Server trả về:", data);

        if (data && data.data) {
            const productListElement = document.getElementById("productList");

            if (productListElement) {
                let productHtml = "";

                data.data.forEach(product => {
                    const encodedFileName = encodeURIComponent(product.image);
                    const imageUrl = `http://localhost:8080/api/product/${encodedFileName}`;

                    productHtml += `
                        <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <img class="img-fluid w-100" src="${imageUrl}" alt="">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3">${product.name}</h6>
                                    <div class="d-flex justify-content-center">
                                        <h6>${product.price}</h6><h6 class="text-muted ml-2 " hidden><del></del></h6>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between bg-light border">
                                    <a href="#" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                    <a href="#" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                </div>
                            </div>
                        </div>
                    `;
                });

                productListElement.innerHTML = productHtml;
            } else {
                console.error("Element with id 'productList' not found.");
            }
        } else {
            console.error("Invalid or empty response from the server.");
        }
    });
});
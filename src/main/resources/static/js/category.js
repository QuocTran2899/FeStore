$(document).ready(function () {
    // Lấy danh sách danh mục
    $.ajax({
        url: "http://localhost:8080/api/category",
        method: "GET",
    }).done(function (data) {
        console.log("Server trả về:", data);

        if (data && data.data) {
            const element = document.getElementById("categoryList");
            let htmlGet = "";
            data.data.forEach(category => {

                htmlGet += `
                    <a href="" class="nav-item nav-link category-link" data-category-id="${category.id}">${category.name}</a>
                `;
            });

            element.innerHTML = htmlGet;

            // Xử lý sự kiện click trên thẻ a
            $(".category-link").on("click", function (event) {
                event.preventDefault();

                // Lấy id của danh mục từ thuộc tính dữ liệu
                const categoryId = $(this).data("category-id");

                // Gọi hàm để lấy sản phẩm theo danh mục
                getByCategoryId(categoryId);
            });
        } else {
            console.error("Invalid or empty response from the server.");
        }
    });

    // Hàm để lấy sản phẩm theo danh mục
    function getByCategoryId(categoryId) {
        $.ajax({
            url: `http://localhost:8080/api/product/category/${categoryId}`,
            method: "GET",
        }).done(function (data) {
            console.log("Sản phẩm theo danh mục:", data);

            // Kiểm tra xem có dữ liệu sản phẩm hay không
            if (data && data.data) {
                const productListElement = document.getElementById("productList");
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
                console.error("Invalid or empty response from the server.");
            }
        }).fail(function () {
            console.error("Failed to fetch products by category.");
        });
    }
});
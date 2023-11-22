$(document).ready(function () {
    var userId = 1;

    $.ajax({
        url: `http://localhost:8080/api/cart/${userId}`,
        method: "GET",
    }).done(function (data) {
        console.log("Server trả về:", data);

        const element = document.getElementById("cartTable");
        let htmlAdd = "";

        if (data && data.data) {
            data.data.forEach(product => {
                htmlAdd += `
                    <tr>
                        <td class="align-middle">
                            <img src="img/product-1.jpg" alt="" style="width: 50px;">${product.name}
                        </td>
                        <td class="align-middle">$150</td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-minus">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control form-control-sm bg-secondary text-center" value="${product.quantity}">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-plus">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle">$150</td>
                        <td class="align-middle">
                            <button class="btn btn-sm btn-primary">
                                <i class="fa fa-times"></i>
                            </button>
                        </td>
                    </tr>
                `;
            });

            element.innerHTML = htmlAdd;
        } else {
            console.error("Invalid or empty response from the server.");
        }
    });
});
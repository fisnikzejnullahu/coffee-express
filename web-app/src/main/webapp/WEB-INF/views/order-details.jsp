<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <jsp:include page="../layout/cssfiles.jsp"/>
    <title>Order details - ${order.orderId}</title>
</head>
<body data-aos-easing="slide" data-aos-duration="800" data-aos-delay="0">
<jsp:include page="../layout/header.jsp"/>

<section class="ftco-section ftco-cart">
    <div class="container">
        <div class="row">
            <div class="col">
                <h4>Order Details:</h4>
            </div>
            <div class="col" style="text-align: end">
                <p>Order Number: ${order.orderId}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 ftco-animate">
                <div class="cart-list" style="overflow: hidden">
                    <table class="table">
                        <thead class="thead-primary">
                        <tr class="text-center">
                            <th>&nbsp;</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.orderDetails.items}" var="item">
                            <tr class="text-center">

                                <td class="image-prod">
                                    <div class="img" style="background-image:url('${item.menuItem.thumbnail}');"></div>
                                </td>

                                <td class="product-name">
                                    <h3>${item.menuItem.name}</h3>
                                </td>

                                <td class="price">$${item.menuItem.price}</td>

                                <td class="quantity">
                                    <div class="input-group mb-3">
                                        <input type="text" name="quantity" class="quantity form-control input-number"
                                               value="${item.quantity}" min="1" max="100">
                                    </div>
                                </td>

                                <td class="total">$ ${doubleFormatter.format(item.totalPrice)} </td>
                            </tr>
                            <!-- END TR-->
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Kitchen details:</h3>
                    <p class="d-flex">
                        <span>Placed at</span>
                        <span>${order.formatTime(order.placedAt)}</span>
                    </p>
                    <p class="d-flex">
                        <span>Accepted at</span>
                        <span>${order.formatTime(order.acceptedAt)}</span>
                    </p>
                    <p class="d-flex">
                        <span>Finished at</span>
                        <span>${order.formatTime(order.finishedAt)}</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Preparation time</span>
                        <span>${order.preparationTime}</span>
                    </p>
                </div>
            </div>
            <div class="col mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>$${doubleFormatter.format(order.orderDetails.totalOfOrder)}</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>$3.00</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>$ ${doubleFormatter.format(order.orderDetails.totalOfOrder)}</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>


</body>
</html>
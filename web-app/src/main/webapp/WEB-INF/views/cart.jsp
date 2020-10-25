<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <jsp:include page="../layout/cssfiles.jsp"/>
    <title>Hello!</title>
</head>
<body data-aos-easing="slide" data-aos-duration="800" data-aos-delay="0">
<jsp:include page="../layout/header.jsp"/>


<section class="ftco-section ftco-cart">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ftco-animate">
                <div class="cart-list" style="overflow: hidden">
                    <table class="table">
                        <thead class="thead-primary">
                        <tr class="text-center">
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cart.items}" var="item">
                            <tr class="text-center">

                                <td class="product-remove">

                                    <form action="${mvc.uri('CartController#removeFromCart')}" method="post">
                                        <a href="javascript:" onclick="parentNode.submit();">
                                            <span class="icon-close"></span>
                                        </a>
                                        <input name="menuItemId" value="${item.menuItem.id}" type="hidden">
                                    </form>
                                </td>
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

                                <td class="total">$${doubleFormatter.format(item.totalPrice)}</td>
                            </tr>
                            <!-- END TR-->
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>$${doubleFormatter.format(cart.totalPrice)}</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>$0.00</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>$${doubleFormatter.format(cart.totalPrice)}</span>
                    </p>
                </div>
                <p class="text-center"><a href="${pageContext.request.contextPath}/mvc/cart/checkout"
                                          class="btn btn-primary py-3 px-4">Proceed to Checkout</a>
                </p>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>


</body>
</html>
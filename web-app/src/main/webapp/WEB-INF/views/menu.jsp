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

<section class="ftco-menu mb-5">
    <div class="container">
        <div class="row d-md-flex">
            <div class="col-lg-12 ftco-animate p-md-5 fadeInUp ftco-animated">
                <div class="row">
                    <div class="col-md-12 d-flex align-items-center">

                        <div class="tab-content ftco-animate fadeInUp ftco-animated" id="v-pills-tabContent">

                            <div class="tab-pane fade show active" id="v-pills-0" role="tabpanel"
                                 aria-labelledby="v-pills-0-tab">
                                <div class="row">
                                    <c:forEach items="${items}" var="item">
                                    <div class="col-md-3">
                                        <div class="menu-entry">
                                            <a href="${pageContext.request.contextPath}/mvc/menu/details/${item.id}" class="img"
                                               style="background-image: url('${item.thumbnail}');"></a>
                                            <div class="text text-center pt-4">
                                                <h3><a href="product-single.html">${item.name}</a></h3>
<%--                                                <p>A small river named Duden flows by their place and supplies</p>--%>
                                                <p class="price"><span>$${item.price}</span></p>
                                                <form action="${mvc.uri('CartController#addInCart')}" method="post">
                                                    <input name="menuItemId" value="${item.id}" type="hidden">
                                                    <input name="quantity" value="1" type="hidden">
                                                    <p><button type="submit" class="btn btn-primary btn-outline-primary">Add
                                                        to
                                                        Cart</button></p>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">
                                <div class="row">
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-1.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-2.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-3.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-4.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-5.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dish-6.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Grilled Beef</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">
                                <div class="row">
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-1.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Lemonade Juice</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-2.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Pineapple Juice</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-3.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Soda Drinks</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-4.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Lemonade Juice</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-5.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Pineapple Juice</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/drink-6.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Soda Drinks</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab">
                                <div class="row">
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-1.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-2.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-3.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-4.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-5.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-center">
                                        <div class="menu-wrap">
                                            <a href="#" class="menu-img img mb-4"
                                               style="background-image: url(../../resources/images/dessert-6.jpg);"></a>
                                            <div class="text">
                                                <h3><a href="product-single.html">Hot Cake Honey</a></h3>
                                                <p>Far far away, behind the word mountains, far from the countries
                                                    Vokalia
                                                    and Consonantia.</p>
                                                <p class="price"><span>$2.90</span></p>
                                                <p><a href="cart.html" class="btn btn-primary btn-outline-primary">Add
                                                    to
                                                    cart</a></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>


</body>
</html>
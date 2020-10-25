<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <jsp:include page="../layout/cssfiles.jsp"/>
    <title>Hello!</title>
</head>
<body data-aos-easing="slide" data-aos-duration="800" data-aos-delay="0">
<jsp:include page="../layout/header.jsp"/>

<section class="ftco-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated">
                <a href="${item.picture}" class="image-popup"><img src="${item.picture}" class="img-fluid"
                                                                   alt="${item.name} picture"></a>
            </div>
            <div class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated">
                <h3>${item.name}</h3>
                <p class="price"><span>$${item.price}</span></p>
                <form id="form" action="${mvc.uri('CartController#addInCart')}" method="post">
                    <input type="hidden" name="menuItemId" value="${item.id}">
                    <div class="row mt-4">
                        <div class="input-group col-md-6 d-flex mb-3">
	             	<span class="input-group-btn mr-2">
	                	<button type="button" class="quantity-left-minus btn" data-type="minus" data-field=""
                                onclick="document.querySelector('#quantity').value = parseInt(document.querySelector('#quantity').value) - 1">
	                   <i class="icon-minus"></i>
	                	</button>
	            		</span>
                            <input type="text" id="quantity" name="quantity" class="form-control input-number" value="1"
                                   min="1" max="100">
                            <span class="input-group-btn ml-2">
	                	<button type="button" class="quantity-right-plus btn" data-type="plus" data-field=""
                                onclick="document.querySelector('#quantity').value = parseInt(document.querySelector('#quantity').value) + 1">
	                     <i class="icon-plus"></i>
	                 </button>
	             	</span>
                        </div>
                    </div>
                    <p>
                        <a class="btn btn-primary py-3 px-5" href="javascript:"
                           onclick="document.querySelector('#form').submit();">
                            Add to Cart
                        </a>
                    </p>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>


</body>
</html>
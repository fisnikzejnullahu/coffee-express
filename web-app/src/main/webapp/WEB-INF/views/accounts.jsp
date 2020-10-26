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

<section class="ftco-section">
    <div class="container">
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel" style="color: inherit;">Delete Confirmation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeModalBtn">Close</button>
                        <button type="button" class="btn btn-danger" id="deleteConfirmBtn">Delete</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${accounts}" var="account">
                <div class="col-4 ftco-animate fadeInUp ftco-animated" div-acc-id="${account.id}">
                    <div class="row mt-2 pt-3 d-flex">
                        <div class="col">
                            <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                                <div class="form-group">
                                    <div class="col">
                                        <p style="
        font-weight: bold;
        color: #fff;
    ">Card</p>
                                        <p>Card Number: ${account.creditCardInfo.cardNumber}</p>
                                        <p>Expiration Date: ${account.creditCardInfo.expirationDate}</p>
                                        <button type="button"
                                                style="background: transparent; border: 0; cursor: pointer;"
                                                data-toggle="modal" data-target="#exampleModal" data-cid="${account.id}">
                            <span style="
                            text-decoration: underline;
                            color: #fff;
                        ">Delete</span>
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>


                </div>

            </c:forEach>

            <!-- .col-md-8 -->


        </div>
    </div>
</section>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/accounts.js"></script>

</body>
</html>
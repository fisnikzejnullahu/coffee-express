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

<div class="container" style="padding-top: 7em">
    <div class="table-responsive" style="overflow: hidden">
        <div class="table-wrapper">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Net Amount</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    int counter = 0;
                    if(request.getParameter("page") != null){
                        counter++;
                        counter *= Integer.parseInt(request.getParameter("page")) * 5;
                    }
                %>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td><%= ++counter %>
                        </td>
                        <td>${order.formatDate(order.placedAt)}</td>
                        <td><span class="status">•</span> ${order.orderState}</td>
                        <td>$${order.orderDetails.totalOfOrder}</td>
                        <td><a href="${mvc.uri('OrderController#details', {'id': order.orderId})}" class="view"
                               title="View Details" data-toggle="tooltip"
                               data-original-title="View Details"><i class="icon-fullscreen-enter"></i></a>
                            <a href="${mvc.uri('OrderController#track', {'id': order.orderId})}" class="view"
                               title="Track" data-toggle="tooltip"
                               data-original-title="Track"><i class="icon-eye"></i></a>
                        </td>
                    </tr>
                    <!-- END TR-->
                </c:forEach>

                </tbody>
            </table>
            <div class="clearfix">
                <div class="row" style="font-size: 1.5em;">
                    <div class="col" style="text-align: end;">
                        <c:choose>
                            <c:when test="${param.page != null && param.page > 0}">
                                <a href="${mvc.uri('OrderController#index', {'page': (param.page-1)})}" class="view"
                                   title="" data-toggle="tooltip"
                                   data-original-title="View Details">Previous</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" class="view text-muted" style="pointer-events: none" title=""
                                   data-toggle="tooltip"
                                   data-original-title="View Details">Previous</a>
                            </c:otherwise>

                        </c:choose>
                    </div>
                    <div class="col" style="text-align: start;">

                        <c:choose>
                            <c:when test="${(param.page == null and 0 < totalPages) || param.page < totalPages}">
                                <a href="${mvc.uri('OrderController#index', {'page': (param.page+1)})}" class="view"
                                   title="" data-toggle="tooltip"
                                   data-original-title="View Details">Next</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" class="view text-muted" style="pointer-events: none" title=""
                                   data-toggle="tooltip"
                                   data-original-title="View Details">Next</a>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>
<jsp:include page="../layout/jsscripts.jsp"/>


</body>
</html>
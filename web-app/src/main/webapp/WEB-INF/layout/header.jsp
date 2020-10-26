<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/mvc/home/">Coffee<small>Express</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item ${current eq "home" ? 'active' : ''}"><a href="${pageContext.request.contextPath}/mvc/home/" class="nav-link">Home</a></li>
                <li class="nav-item ${current eq "menu" ? 'active' : ''}"><a href="${pageContext.request.contextPath}/mvc/menu/" class="nav-link">Menu</a></li>
                <li class="nav-item ${current eq "orders" ? 'active' : ''}"><a href="${pageContext.request.contextPath}/mvc/orders/" class="nav-link">My Orders</a></li>
                <li class="nav-item ${current eq "account" ? 'active' : ''}"><a href="${pageContext.request.contextPath}/mvc/accounts/" class="nav-link">My Bank Accounts</a></li>
                <li class="nav-item cart"><a href="${pageContext.request.contextPath}/mvc/cart" class="nav-link"><span class="icon icon-shopping_cart"></span><span class="bag d-flex justify-content-center align-items-center"><small>${cart.items.size()}</small></span></a></li>
            </ul>
        </div>
    </div>
</nav>